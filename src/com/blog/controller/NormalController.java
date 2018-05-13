package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.Blog;
import com.blog.domain.User;
import com.blog.service.AccountService;
import com.blog.service.NormalService;
import com.blog.util.request.CreateUserData;
import com.blog.util.request.LoginData;
import com.blog.util.response.BlogList;

@Controller
public class NormalController {

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;
	
	@Autowired
	@Qualifier("normalServiceImpl")
	private NormalService normalService;
	
	// 注册函数
	public int register(CreateUserData data) {
		return accountService.createUser(data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int register(@RequestBody User user) {
		return accountService.createUser_(user);
	}
	
	// 登陆函数
	@ResponseBody
	@RequestMapping("/login")
	public int login(@RequestBody LoginData data) {//不通过
		// 到时候看看是返回id还是返回状态码
		return accountService.login(data);
	}
	
	// 查看博客列表
	@ResponseBody
	@RequestMapping("/showBlog")
	public List<BlogList> blogLists() {//通过
		return normalService.readBlog();
	}
	
	// 查看某一条博客的具体信息
	@ResponseBody
	@RequestMapping("/blogdetail/{blogId}")
	public Blog blogDetail(@PathVariable("blogId") String blogId) {//不通过 could not write json
		return normalService.findBlogById(blogId);
	}
}
