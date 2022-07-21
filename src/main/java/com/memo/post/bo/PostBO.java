package com.memo.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.dao.PostDAO;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		// file이 있으면 file 업로드 => image path 리턴 받음
		String imagePath = "";
		
		// dao insert
		postDAO.insertPost(userId, subject, content, imagePath);
	}
	
}
