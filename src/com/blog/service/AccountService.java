package com.blog.service;

import com.blog.domain.User;
import com.blog.util.request.CreateUserData;
import com.blog.util.request.LoginData;

public interface AccountService {
	
	
	int createUser_(User data);
	
	int createUser(CreateUserData data);
	
	int login(LoginData data);
}
