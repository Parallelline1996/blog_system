package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.blog.domain.User;
import com.blog.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

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
