package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.service.AccountService;
import com.blog.util.request.CreateUserData;
import com.blog.util.request.LoginData;

@Service
@Qualifier("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Override
	public int createUser(CreateUserData data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int login(LoginData data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
