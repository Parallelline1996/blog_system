package com.blog.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("userDaoImpl")
public class UserDaoImpl extends HibernateUtil implements UserDao {

	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> allUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserData(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int newUserNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
