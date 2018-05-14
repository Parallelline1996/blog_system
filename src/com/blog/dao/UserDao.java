package com.blog.dao;

import java.util.List;

import com.blog.domain.User;

public interface UserDao {

	boolean createUser(User user);
	
	User findUserById(Integer userId);
	
	List<User> allUser();
	
	List<User> allUserByPage(int page);
	
	boolean deleteUser(Integer userId);
	
	boolean updateUserData(User user);
	
	boolean accountExist(String account);
	
	int checkPassword(String email,String password);
}
