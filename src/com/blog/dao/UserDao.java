package com.blog.dao;

import java.util.List;

import com.blog.domain.User;

public interface UserDao {

	boolean createUser(User user);
	
	User findUserById(String userId);
	
	List<User> allUser();
	
	boolean deleteUser(String userId);
	
	boolean updateUserData(User user);
	
	// 用于创建新的对象时，查看数据库的情况并生成id
	int newUserNumber();
}
