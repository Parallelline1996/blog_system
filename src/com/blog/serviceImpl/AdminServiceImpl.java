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
		// TODO Auto-generated method stub
		return userDao.allUser();
	}

	@Override
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.findUserById(userId);
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		if(userDao.findUserById(userId).getStatus()==1)
		{
			return false;
		}
		if(userDao.deleteUser(userId)==true)
		{
			return true;
		}
		else {
			return false;
		}
	}
}
