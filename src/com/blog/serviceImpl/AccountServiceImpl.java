package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
	public int login(@RequestBody LoginData data) {
		// TODO Auto-generated method stub
		// 返回2代表是管理员账号，1代表普通用户
		//0代表用户不存在账号被封禁,-1代表用户密码错误，-2代表管理员密码错误。

		if(userDao.accountExist(data.getEmail())) {//通过
			if(userDao.checkPassword(data.getEmail(), data.getPassword())) {//通过
				return 1;
			}
			else {
				return -1;
			}
		}

		else if(adminDao.adminExist(data.getEmail())){//测试不通过
			if(adminDao.checkPassword(data.getEmail(), data.getPassword())){//测试不通过
				return 2;
			}
			else {
				return -2;
			}
		}
		else {
			return 0;
		}

	}

}
