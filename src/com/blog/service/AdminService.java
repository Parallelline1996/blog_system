package com.blog.service;

import java.util.List;

import com.blog.domain.User;

public interface AdminService {

	//List<User> allUsers();
	
	/**
	 * 分页显示所有的用户
	 * @param page 页码
	 * @return 用户list
	 */
	List<User> allUserByPage(int page);

	User findUserById(Integer userId);
	
	boolean deleteUser(Integer userId);
}
