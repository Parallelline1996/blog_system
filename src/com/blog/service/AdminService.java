package com.blog.service;

import java.util.List;

import com.blog.domain.User;

public interface AdminService {

	List<User> allUsers();
	
	List<User> allUserByPage(int page);

	User findUserById(Integer userId);
	
	boolean deleteUser(Integer userId);
}
