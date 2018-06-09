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

	/**
	 * 通过用户 id 查找用户信息
	 * @param userId 用户id
	 * @return 用户信息
	 */
	User findUserById(Integer userId);
	
	/**
	 * 通过用户 id 删除用户
	 * @param userId 用户id
	 * @return boolean
	 */
	boolean deleteUser(Integer userId);
}
