package com.memo.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
//	private Logger logger = LoggerFactory.getLogger(PostBO.class);		// slf4j.Logger
	private Logger logger = LoggerFactory.getLogger(this.getClass());	// sysout 대신에 로그 찍어서 확인
	
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
	
	public int updatePost(int userId, String userLoginId, int postId, String subject, String content, MultipartFile file) {
		logger.info("updatePost postId:{}, userId:{}", postId, userId);
		
		// 기존에 저장된 글을 가져옴 (수정할 때 전에 업로드 했던 사진이 날아가는 것을 방지)
		Post post = getPostById(postId);
		if(post == null) {
			logger.error("[update post] 수정할 게시물이 없습니다. postId:{}",postId);
			return 0;		// 밑에 코드 수행X
		}
		
		// upload할 file이 없는 경우 수정하지 않음
		String imagePath = null;
		// upload할 file이 있는지 본 후 서버에 upload하고 imagePath를 받아온다.
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
			
			// 새로운 이미지가 존재하면 기존 이미지 삭제 (기존 이미지가 있을 때에만)
			if(imagePath != null && post.getImagePath() != null) {
				// 기존 이미지 삭제
				try {
					fileManager.deleteFile(post.getImagePath());
				} catch (IOException e) {
					logger.error("이미지 삭제 실패. postId:{}",postId);
				}
			}
		}
		
		// dao update
		return postDAO.updatePostByPostIdAndUserId(postId, userId, subject, content, imagePath);	// 성공하면 returns 1
	}

	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
	}

	public Post getPostById(int id) {
		return postDAO.selectPostById(id);
	}

	public void deletePost(int postId, int userId) {
		// 삭제 전에 imagePath가 있을 수 있으므로 Post를 먼저 가져와 본다.
		Post post = getPostById(postId);
		if(post == null) {	// 삭제할 post가 없으면 error
			logger.error("[delete post] 삭제할 게시물이 없습니다. postId:{}",postId);
			return;			// 메소드 종료
		}
		
		if(post.getImagePath() != null) {
			// 삭제할 이미지가 존재하면 이미지 파일 삭제
			try {
				fileManager.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("이미지 삭제 실패. postId:{}, path:{}", postId, post.getImagePath());
			}
		}
		postDAO.deletePostByPostIdAndUserId(postId, userId);
	}
	
}
