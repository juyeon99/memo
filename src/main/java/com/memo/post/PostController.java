package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.post.bo.PostBO;
import com.memo.post.model.Post;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;

	// localhost/post/post_list_view
	@RequestMapping("/post_list_view")
	public String postListView(Model model, HttpSession session) {
		List<Post> postList = postBO.getPostList((int)session.getAttribute("userId"));
		model.addAttribute("postList",postList);
		model.addAttribute("viewName", "post/post_list");
		return "template/layout";
	}
	
	/**
	 * 글쓰기 화면
	 * @param model
	 * @return
	 */
	// localhost/post/post_create_view
	@RequestMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/post_create");
		return "template/layout";
	}
	
	/**
	 * 글 상세 화면
	 * @param model
	 * @param postId
	 * @return
	 */
	// localhost/post/post_detail_view
	@RequestMapping("/post_detail_view")
	public String postDetailView(Model model,
			@RequestParam("postId") int postId) {
		// select db by id
		Post post = postBO.getPostById(postId);
		
		model.addAttribute("viewName", "post/post_detail");
		model.addAttribute("post", post);
		
		return "template/layout";
	}
	
}
