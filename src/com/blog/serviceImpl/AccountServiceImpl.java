package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.dao.AdminDao;
import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.AccountService;
import com.blog.util.request.CreateUserData;
import com.blog.util.request.LoginData;

@Service
@Qualifier("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("adminDaoImpl")
	private AdminDao adminDao;
	
	@Override
	public int createUser(CreateUserData data) {
		return createUser(data);
	}
	
	// 测试用，未完成
	public int createUser_(User user) {
		if (userDao.createUser(user))
			return 200;
		else {
			return 0;
		}
	}

	@Override
	public int login(LoginData data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
