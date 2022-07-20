package com.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 화면
@RequestMapping("/user")
@Controller
public class UserController {

	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	// localhost/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/sign_up");
		return "template/layout";
	}

	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	// localhost/user/sign_in_view
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/login");
		return "template/layout";
	}
	
	/**
	 * 회원가입 수행(form) - 사용하지 않음
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
//	@PostMapping("/sign_up_for_submit")
//	public String signUpForSubmit(
//			@RequestParam("loginId") String loginId,
//			@RequestParam("password") String password,
//			@RequestParam("name") String name,
//			@RequestParam("email") String email) {
//		// TODO DB insert
//		
//		return "redirect:/user/sign_in_view";
//	}
	
	// localhost/user/sign_out
	@RequestMapping("/sign_out")
//	public String signOut(HttpServletRequest req) {
//		HttpSession session = req.getSession();
	public String signOut(HttpSession session) {
		// 로그아웃 - 세션에 있는 모든 key-value들을 지움
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign_in_view";
	}
	
}
