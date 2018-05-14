package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.FollowDao;
import com.blog.dao.UserDao;
import com.blog.domain.Blog;
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
	
	@Override
	public boolean createFollow(Integer ownId, Integer userId) {
		// 粉丝在前，博主在后
		FollowUpId followUpId = new FollowUpId(userId, ownId);
		Follow follow = new Follow(followUpId);
		User fans = userDao.findUserById(userId);
		User blogger = userDao.findUserById(ownId);
		if(fans!=null){
			fans.setNumOfAttention(fans.getNumOfAttention() + 1);
			
		}
		if(blogger!=null) {
			blogger.setNumOfFans(blogger.getNumOfFans() + 1);
			
		}
		return save(follow);
	}

	@Override
	public boolean deleteFollow(Integer ownId, Integer userId) {
		FollowUpId followUpId = new FollowUpId(userId, ownId);
		Follow follow = new Follow(followUpId);
		User fans = userDao.findUserById(userId);
		User blogger = userDao.findUserById(ownId);
		if(fans!=null){
			fans.setNumOfAttention(fans.getNumOfAttention() - 1);
		}
		if(blogger!=null) {
			blogger.setNumOfFans(blogger.getNumOfFans() - 1);
		}
		return delete(follow);
	}

	@Override
	public List<String> visitFans(Integer ownId) {
		// TODO Auto-generated method stub
		// 待完成 好像不行
		String hql = "from FollowUpId where ownId = ?";
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<FollowUpId> res = null;
		List<String> s = null;
		Query query = null;
		try {
			query = session.createQuery(hql).setParameter(0, ownId);
			res = query.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		if(res!=null)
		 {
/*
			for(FollowUpId FollowUpId : res)
			{
				s.add(FollowUpId.getBloggerId());
			}*/
		
		}
		return s;
	}

	@Override
	public List<String> visitFollow(Integer ownId) {
		// TODO Auto-generated method stub
		// 待完成,好像不行
		String hql = "from FollowUpId where userId = ?";
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<FollowUpId> res = null;
		List<String> s = null;
		Query query = null;
		try {
			query = session.createQuery(hql).setParameter(0, ownId);
			res = query.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		if(res!=null)
		 {
/*
			for(FollowUpId FollowUpId : res)
			{
				s.add(FollowUpId.getBloggerId());
			}*/
		}
		return s;
	}

	@Override
	public int numberOfFollows(Integer ownId) {
		// TODO Auto-generated method stub
		// 似乎这两个都没有必要
		return 0;
	}

	@Override
	public int numberOfFans(Integer ownId) {
		// TODO Auto-generated method stub
		return 0;
	}

}