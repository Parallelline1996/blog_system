package com.blog.service;

import com.blog.domain.User;
import com.blog.util.request.LoginData;

public interface AccountService {
	
	
	int createUser(User data);
	
	int login(LoginData data);
}
