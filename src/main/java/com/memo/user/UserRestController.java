package com.memo.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
import com.memo.user.bo.UserBO;
import com.memo.user.model.User;

// 데이터 API
@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/user_list")
	public List<User> userList(){
		return userBO.getUserList();
	}
	
	/**
	 * 아이디 중복확인
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		
		boolean existLoginId = userBO.existLoginId(loginId);
		Map<String, Object> result = new HashMap<>();
		result.put("result", existLoginId);
		
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		
		// 비밀번호 해싱(~=암호화)(md5)		(SHA256/SHA512 are safer)
		String encryptedPassword = EncryptUtils.md5(password);
		
		// db insert
		userBO.addUser(loginId,encryptedPassword,name,email);
		
		// return result
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest req){
		String encryptedPassword = EncryptUtils.md5(password);
		
		User user = userBO.getUserByLoginId(loginId,encryptedPassword);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			// session - 로그인이 성공하면 로그인 상태 유지를 위해 세션에 사용자에 대한 필요한 정보를 담는다.
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("result", "success");
		} else {
			// 실패하면 실패 응답
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}
		
		return result;
	}
}
