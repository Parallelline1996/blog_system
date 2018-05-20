package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.dao.AdminDao;
import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.AccountService;
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
	public int createUser(User user) {
		if (userDao.accountExist(user.geteMail())) {
			// 邮箱已经被注册
			return -1;
		} else {
			user.setStatus(0);
			user.setNumOfAttention(0);
			user.setNumOfFans(0);
			// 照片这里暂时不处理，等待处理
			user.setProfile(null);
			if (userDao.createUser(user)) {
				return 200;
			} else {
				// 系统异常
				return -2;
			}
		}
	}

	@Override
	public int login(@RequestBody LoginData data) {
		int code = data.getCode();
		// 代表是普通用户
		if (code != 1) {
			if(userDao.accountExist(data.getEmail())) {
				int temp = userDao.checkPassword(data.getEmail(), data.getPassword());
				if (temp > 0) {
					return temp;
				} else {
					// -2表示密码错误
					return -2;
				}
			} else {
				// -1代表账号不存在
				return -1;
			}
		} else {
			if (adminDao.adminExist(data.getEmail())) {
				int temp = adminDao.checkPassword(data.getEmail(), data.getPassword());
				if (temp > 0) {
					return temp;
				} else {
					// -2表示密码错误
					return -2;
				}
			} else {
				// -1代表账号不存在
				return -1;
			}
		}
	}

}
