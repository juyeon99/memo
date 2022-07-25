package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

// 데이터 API
@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String,Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 글쓴이 정보를 세션에서 꺼낸다.
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {	// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// 로그인 되어있음
		int userId = (int) userIdObj;
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 글쓰기 db insert
		postBO.addPost(userId, userLoginId, subject, content, file);
		
		return result;
	}
	
	@PostMapping("/update")
	public Map<String,Object> update(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam("postId") int postId,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 글쓴이 정보를 세션에서 꺼낸다.
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {	// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// 로그인 되어있음
//		int userId = (int) userIdObj;
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 글쓰기 db update
		postBO.updatePostById(postId, userLoginId, subject, content, file);
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String,Object> delete(
			@RequestParam("deleteId") int id){
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		postBO.deletePostById(id);
		
		return result;
	}
	
}
