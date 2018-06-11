package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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

	@Override
	public int numberOfUser() {
		String hql = "from User where status = 0";
		int number = -1;
		Session session = getSessionFactory().openSession();
		Query query = null;
		try {
			query = session.createQuery(hql);
			number = (int)query.list().size();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		
		return number;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> bestUser() {
		String hql = "from User where status = 0";
		List<Object> objects = null;
		Session session = sessionFactory.openSession();
		Query query = null;
		try {
			query = session.createQuery(hql);
			objects = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (objects == null) {
			return null;
		}
		int number = objects.size();
		int[] location = new int[number];
		for (int i = 0; i < number; i++) {
			location[i] = i;
		}
		int[] temp = new int[number];
		for (int i = 0; i < number; i++) {
			User user = (User)objects.get(i);
			temp[i] = user.getNumOfFans();
		}
		for (int i = 1; i < number; i++) {
			for (int j = 0; j < number - i; j++) {
				if (temp[j] < temp[j + 1]) {
					int temp_ = temp[j + 1];
					temp[j + 1] = temp[j];
					temp[j] = temp_;
					temp_ = location[j + 1];
					location[j + 1] = location[j];
					location[j] = temp_;
				}
			}
		}
		List<User> users = new ArrayList<>();
		if (number <= 6) {
			for (int i = 0; i < number; i++) {
				User user = (User)objects.get(location[i]);
				users.add(user);
			}
		} else {
			for (int i = 0; i < 6; i++) {
				User user = (User)objects.get(location[i]);
				users.add(user);
			}
		}
		return users;
	}

}
