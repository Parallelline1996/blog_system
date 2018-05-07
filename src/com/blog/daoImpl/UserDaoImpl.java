package com.blog.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.util.HibernateUtil;


@Repository
@Qualifier("userDaoImpl")
public class UserDaoImpl extends HibernateUtil implements UserDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createUser(User user) {
		return save(user);
	}

	@Override
	public User findUserById(String userId) {
		Session session = sessionFactory.openSession();
		User user = null;
		try {
			user = (User)session.get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> allUser() {
		String hql = "from user";
		return (List<User>)findByHql(hql, null);
	}

	@Override
	public boolean deleteUser(String userId) {
		User user = findUserById(userId);
		user.setStatus(1);
		return true;
	}

	@Override
	public boolean updateUserData(User user) {
		return update(user);
	}

	@Override
	public int newUserNumber() {
		// 似乎，不需要用。。暂时不写
		return 0;
	}

}
