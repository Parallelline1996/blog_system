package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.User;
import com.blog.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

	// 返回所有的用户信息
	@ResponseBody
	@RequestMapping("/allusers")
	public List<User> allUser() {
		return adminService.allUsers();
	}
	
	// 按照id查找用户信息
	@ResponseBody
	@RequestMapping("/findUserByID/{userId}")//通过
	public User findUser(@PathVariable("userId") String userId) {
		return adminService.findUserById(userId);
	}

	// 删除用户
	@ResponseBody
	@RequestMapping("/deleteUser/{userId}")//通过
	public int deleteUser(@PathVariable("userId") String userId) {
		if(adminService.deleteUser(userId)==true) {
			return 200;
		}
		else {
			return -1;
		}
	}
}
