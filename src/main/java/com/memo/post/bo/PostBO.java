package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		
		// file이 있으면 file 업로드 => image path 리턴 받음
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		// dao insert
		postDAO.insertPost(userId, subject, content, imagePath);
	}
	
	public void updatePostById(int id, String userLoginId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		
		// file이 있으면 file 업로드 => image path 리턴 받음
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		// dao update
		postDAO.updatePostById(id, subject, content, imagePath);
	}

	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
	}

	public Post getPostDetailById(int id) {
		return postDAO.selectPostDetailById(id);
	}

	public void deletePostById(int id) {
		postDAO.deletePostById(id);
	}
	
}
