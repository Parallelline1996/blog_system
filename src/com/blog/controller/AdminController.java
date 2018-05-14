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
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

	// 返回所有的用户信息
	// 这里要进行分页操作，参加参数选页数，固定每页5个data
	@ResponseBody
	@RequestMapping("/allUsers")
	public List<User> allUser() {
		return adminService.allUsers();
	}
	
	// 按照id查找用户信息
	@ResponseBody
	@RequestMapping("/findUserByID/{userId}")
	public User findUser(@PathVariable("userId") String userId) {
		return adminService.findUserById(userId);
	}

	// 删除用户
	@ResponseBody
	@RequestMapping("/deleteUser/{userId}")
	public int deleteUser(@PathVariable("userId") String userId) {
		if (adminService.deleteUser(userId)) {
			return 200;
		} else {
			return -1;
		}
	}
}
