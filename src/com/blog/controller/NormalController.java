package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	// 注册功能
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int register(@RequestBody User user) {
		return accountService.createUser(user);
	}
	
	// 登陆函数
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody LoginData data, HttpServletRequest request) {
		// 未完成，当登陆成功时，应记录登陆用户的类型 + 编号
		HttpSession session = request.getSession();
		session.setAttribute("", "");
		session.setAttribute("", "");
		return accountService.login(data);
	}
	
	// 查看博客列表
	@ResponseBody
	@RequestMapping(value = "/showBlog", method = RequestMethod.GET)
	public List<BlogList> blogLists() {
		// 作了调整，未完成
		return normalService.readBlog();
	}
	
	// 查看某一条博客的具体信息
	@ResponseBody
	@RequestMapping("/blogDetail/{blogId}")
	public Blog blogDetail(@PathVariable("blogId") String blogId) {
		// could not write json  com.blog.domain.Blog["tags"]
		return normalService.findBlogById(Integer.parseInt(blogId));
	}
}
