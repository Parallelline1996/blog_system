package com.blog.daoImpl;

import java.util.ArrayList;
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
	public User findUserById(Integer userId) {
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

	@Override
	public List<User> allUser() {
		// 0表示可用
		String hql = "from User where u_status = 0";
		List<Object> temp = findByHqlGetList(hql);
		List<User> users = new ArrayList<>();
		for (Object t : temp) {
			users.add((User)t);
		}
		return users;
	}

	@Override
	public List<User> allUserByPage(int page) {
		String hql = "from User where u_status = 0";
		// 中间参数是第几页的意思
		List<Object> temp = listpage(hql, page, 5);
		List<User> users = new ArrayList<>();
		for (Object t : temp) {
			users.add((User)t);
		}
		return users;
	}
	
	@Override
	public boolean deleteUser(Integer userId) {
		User user = findUserById(userId);
		user.setStatus(1);
		return save(user);
	}

	@Override
	public boolean updateUserData(User user) {
		return update(user);
	}
	
	@Override
	public boolean accountExist(String eMail) {
		//测试通过
		Session session = sessionFactory.openSession();
		String hql = "from User where eMail = ? and status = ? ";
		User user = null;
		try {
			// 当确定返回值为1个或null时，使用uniqueResult
			user = (User)session.createQuery(hql)
					.setParameter(0, eMail).setParameter(1, 0)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 是否存在，存在的话返回的是true
		if (user != null)
			return true;
		return false;
	}
	
	@Override
	public int checkPassword(String email,String password) {
		Session session = sessionFactory.openSession();
		String hql = "from User where  password = ? and eMail = ?";
		User user = null;
		try {
			// 当确定返回值为1个或null时，使用uniqueResult
			user = (User)session.createQuery(hql)
					.setParameter(0,password).setParameter(1, email)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 是否存在，存在的话返回的是true
		if (user == null)
			return -1;
		return user.getUserId();
	}

}
