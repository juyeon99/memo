package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	// localhost/user/login_view
	@RequestMapping("/login_view")
	public String loginView(Model model) {
		model.addAttribute("viewName", "user/login");
		return "template/layout";
	}

}
