package com.blog.dao;

import com.blog.domain.Admin;

public interface AdminDao {
	
	Admin findAdminById(String adminId);
	
	boolean adminExist(String account);
	
	int checkPassword(String email,String password);
}
