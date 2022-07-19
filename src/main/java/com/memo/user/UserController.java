package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 화면
@RequestMapping("/user")
@Controller
public class UserController {

	// localhost/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/sign_up");
		return "template/layout";
	}

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
	// localhost/user/sign_up_for_submit
	@PostMapping("/sign_up_for_submit")
	public String signUpForSubmit(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		// TODO DB insert
		
		return "redirect:/user/sign_in_view";
	}
	
}
