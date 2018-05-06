package com.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.blog.domain.User;

@Controller
public class AdminController {

	// 返回所有的用户信息
	public List<User> allUser() {
		return null;
	}
	
	// 按照id查找用户信息
	public User findUser(String userId) {
		return null;
	}

	// 删除用户
	public int deleteUser(String userId) {
		return 0;
	}
}
