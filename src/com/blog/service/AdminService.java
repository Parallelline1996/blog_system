package com.blog.service;

import java.util.List;

import com.blog.domain.User;

public interface AdminService {

	List<User> allUsers();

	User findUserById(String userId);
	
	boolean deleteUser(String userId);
}
