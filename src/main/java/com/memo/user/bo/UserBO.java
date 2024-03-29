package com.memo.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.dao.UserDAO;
import com.memo.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;

	public List<User> getUserList() {
		return userDAO.selectUserList();
	}
	
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}

	public User getUserByLoginId(String loginId, String password) {
		return userDAO.selectUserByLoginId(loginId,password);
	}

}
