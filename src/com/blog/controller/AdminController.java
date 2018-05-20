package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.User;
import com.blog.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	// 差一个管理员的检验问题，以及删除时的联级问题
	
	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;
	
	@Autowired
	private HttpServletRequest request;

	// 返回所有的用户信息
	@ResponseBody
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public List<User> allUser() {
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return null;
		}
		return adminService.allUsers();
	}
	
	// 对上方的函数进行分页处理，固定为每页5条，传入参数为第几页
	@ResponseBody
	@RequestMapping(value = "/allUsers/{pageNumber}", method = RequestMethod.GET)
	public List<User> allUserByPage(@PathVariable("pageNumber") String pageNumber){
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return null;
		}
		return adminService.allUserByPage(Integer.parseInt(pageNumber));
	}
	
	// 按照id查找用户信息
	@ResponseBody
	@RequestMapping(value = "/findUserByID/{userId}", method = RequestMethod.GET)
	public User findUser(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return null;
		}
		return adminService.findUserById(userId);
	}

	// 删除用户
	@ResponseBody
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public int deleteUser(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return 404;
		}
		if (adminService.deleteUser(userId)) {
			return 200;
		} else {
			return -1;
		}
	}
}
