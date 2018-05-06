package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.Blog;
import com.blog.domain.User;
import com.blog.service.AccountService;
import com.blog.util.request.CreateUserData;
import com.blog.util.request.LoginData;
import com.blog.util.response.BlogList;

@Controller
public class MormalController {

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;
	
	// 注册函数
	public int register(CreateUserData data) {
		return accountService.createUser(data);
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public int register(@RequestBody User user) {
		return accountService.createUser_(user);
	}
	
	// 登陆函数
	public int login(LoginData data) {
		// 到时候看看是返回id还是返回状态码
		return 1;
	}
	
	// 查看博客列表
	public List<BlogList> blogLists() {
		return null;
	}
	
	// 查看某一条博客的具体信息
	public Blog blogDetail(String blogId) {
		return null;
	}
}
