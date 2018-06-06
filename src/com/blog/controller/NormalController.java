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

import com.blog.domain.User;
import com.blog.service.AccountService;
import com.blog.service.NormalService;
import com.blog.util.request.LoginData;
import com.blog.util.response.BlogData;
import com.blog.util.response.BlogList;

@Controller
public class NormalController {

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;
	
	@Autowired
	@Qualifier("normalServiceImpl")
	private NormalService normalService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注册函数，用于新用户注册
	 * @param user 用户的注册信息，包括昵称、密码、邮箱、手机号码
	 * @return 整数类型，200 代表注册成功，-1 代表邮箱已经被注册，-2 代表系统异常
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int register(@RequestBody User user) {
		return accountService.createUser(user);
	}
	
	/**
	 * 登陆函数，用户注册用户的登陆
	 * @param data 用户用于登陆的信息，包括密码、邮箱账号、以及code
	 * @return 整数类型，200 代表登陆成功，-1 代表该邮箱还未注册，-2 代表密码错误
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody LoginData data) {
		int temp = accountService.login(data);
		// 成功登陆
		if (temp > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", temp);
			if (data.getCode() == 1) {
				// 如果用户为管理员，做标记
				session.setAttribute("isAdmin", temp);
			}
		}
		return temp;
	}
	
	/**
	 * 返回博客列表 这里博客的范围是所有的博客
	 * @param page 第几页
	 * @return 返回对应页的博客列表
	 */
	@ResponseBody
	@RequestMapping(value = "/showBlog/{page}", method = RequestMethod.GET)
	public List<BlogList> blogListByPage(@PathVariable("page") String page) {
		return normalService.readBlogByPage(Integer.parseInt(page));
	}
	
	/**
	 * 查看某一个博客的具体信息
	 * @param blogId 博客的id
	 * @return 返回博客的具体信息
	 */
	@ResponseBody
	@RequestMapping("/blogDetail/{blogId}")
	public BlogData blogDetail(@PathVariable("blogId") String blogId) {
		return normalService.findBlogById(Integer.parseInt(blogId));
	}
}
