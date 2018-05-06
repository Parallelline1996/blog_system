package com.blog.service;

import com.blog.util.request.CreateUserData;
import com.blog.util.request.LoginData;

public interface AccountService {
	
	int createUser(CreateUserData data);
	
	int login(LoginData data);
}
