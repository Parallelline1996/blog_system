package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.FollowDao;
import com.blog.dao.UserDao;
import com.blog.domain.Follow;
import com.blog.domain.FollowUpId;
import com.blog.domain.User;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("follwoDaoImpl")
public class FollowDaoImpl extends HibernateUtil implements FollowDao{

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createFollow(Integer fanId, Integer ownId) {
		// 粉丝在前，被关注的人在后
		FollowUpId followUpId = new FollowUpId(fanId, ownId);
		Follow follow = new Follow(followUpId);
		// 获得实体类对象
		User fans = userDao.findUserById(fanId);
		User blogger = userDao.findUserById(ownId);
		if (fans == null || blogger == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean temp = false;
		try {
			// 关注时，加入follow_up表，并更改对应用户的粉丝数和关注人数
			fans.setNumOfAttention(fans.getNumOfAttention() + 1);
			blogger.setNumOfFans(blogger.getNumOfFans() + 1);
			userDao.updateUserData(fans);
			userDao.updateUserData(blogger);
			save(follow);
			temp = true;
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return temp;
	}

	@Override
	public boolean deleteFollow(Integer fanId, Integer userId) {
		FollowUpId followUpId = new FollowUpId(fanId, userId);
		Follow follow = new Follow(followUpId);
		User fans = userDao.findUserById(fanId);
		User blogger = userDao.findUserById(userId);
		if (fans == null || blogger == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean temp = false;
		try {
			// 取消关注时，从follow_up去除，并更改对应用户的粉丝数和关注人数
			fans.setNumOfAttention(fans.getNumOfAttention() - 1);
			blogger.setNumOfFans(blogger.getNumOfFans() - 1);
			userDao.updateUserData(fans);
			userDao.updateUserData(blogger);
			delete(follow);
			temp = true;
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return temp;
	}

	// 判断是否已经关注了
	@Override
	public boolean existFollow(Integer ownId, Integer userId) {
		String hql = "from Follow where fans_ID = ? and blogger_ID = ?";
		Session session = sessionFactory.openSession();
		Follow follow = null;
		try {
			follow = (Follow)session.createQuery(hql).setParameter(0, ownId)
					.setParameter(1, userId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (follow == null)
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> visitFans(Integer ownId) {
		String hql = "from Follow where blogger_ID = ?";
		Session session = getSessionFactory().openSession();
		List<Object> res = null;
		Query query = null;
		List<Integer> s = new ArrayList<>();
		try {
			query = session.createQuery(hql).setParameter(0, ownId);
			res = query.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		if (res != null) {
			for (Object object : res) {
				Follow follow = (Follow)object;
				s.add(follow.getFollowUpId().getFansId());
			}
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> visitFollow(Integer ownId) {
		String hql = "from Follow where fans_ID = ?";
		Session session = getSessionFactory().openSession();
		List<Object> res = null;
		Query query = null;
		List<Integer> s = new ArrayList<>();
		try {
			query = session.createQuery(hql).setParameter(0, ownId);
			res = query.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		if (res != null) {
			for (Object object : res) {
				Follow follow = (Follow)object;
				s.add(follow.getFollowUpId().getBloggerId());
			}
		}
		return s;
	}
}