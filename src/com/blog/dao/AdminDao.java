package com.blog.dao;

import com.blog.domain.Admin;

public interface AdminDao {
	
	Admin findAdminById(String adminId);
}
