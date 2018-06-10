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
import com.blog.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 返回用户列表
	 * @param pageNumber 页码
	 * @return 返回某一页的所有用户信息
	 */
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
	
	/**
	 * 按照id查找用户信息
	 * @param userId
	 * @return 返回用户的具体信息
	 */
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

	/**
	 * 删除用户
	 * @param userId
	 * @return 整数类型，404 代表非管理员操作，-1 代表删除失败，200 代表删除成功
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public int deleteUser(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return 404;
		}
		// 这里要修改一下，当删除用户时，他发表过的 blog 都要置为删除状态
		// 评论不做处理
		if (adminService.deleteUser(userId)) {
			return 200;
		} else {
			return -1;
		}
	}
	
	/**
	 * 用户总数目
	 * @return 整数类型
	 */
	@ResponseBody
	@RequestMapping(value = "/numberOfUser", method = RequestMethod.GET)
	public int numberOfUser() {
		return userService.numberOfUser();
	}
}
