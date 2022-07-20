package com.memo.post;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

	// localhost/post/post_list_view
	@RequestMapping("/post_list_view")
	public String postListView(Model model) {
		model.addAttribute("viewName", "post/post_list");
		return "template/layout";
	}
}