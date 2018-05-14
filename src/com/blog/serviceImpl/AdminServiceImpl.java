package com.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.AdminService;

@Service
@Qualifier("adminServiceImpl")
public class AdminServiceImpl implements AdminService {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Override
	public List<User> allUsers() {
		return userDao.allUser();
	}
	
	@Override
	public List<User> allUserByPage(int page) {
		return userDao.allUserByPage(page);
	}

	@Override
	public User findUserById(Integer userId) {
		return userDao.findUserById(userId);
	}

	@Override
	public boolean deleteUser(Integer userId) {
		if (userDao.findUserById(userId).getStatus() == 1) {
			return false;
		}
		return userDao.deleteUser(userId);
	}
}
