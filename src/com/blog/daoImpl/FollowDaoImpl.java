package com.blog.daoImpl;

import java.util.List;

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
	
	@Override
	public boolean createFollow(String ownId, String userId) {
		// 粉丝在前，博主在后
		FollowUpId followUpId = new FollowUpId(userId, ownId);
		Follow follow = new Follow(followUpId);
		User fans = userDao.findUserById(userId);
		User blogger = userDao.findUserById(ownId);
		fans.setNumOfAttention(fans.getNumOfAttention() + 1);
		blogger.setNumOfFans(blogger.getNumOfFans() + 1);
		return save(follow);
	}

	@Override
	public boolean deleteFollow(String ownId, String userId) {
		FollowUpId followUpId = new FollowUpId(userId, ownId);
		Follow follow = new Follow(followUpId);
		User fans = userDao.findUserById(userId);
		User blogger = userDao.findUserById(ownId);
		fans.setNumOfAttention(fans.getNumOfAttention() - 1);
		blogger.setNumOfFans(blogger.getNumOfFans() - 1);
		return delete(follow);
	}

	@Override
	public List<String> visitFans(String ownId) {
		// TODO Auto-generated method stub
		// 待完成
		return null;
	}

	@Override
	public List<String> visitFollow(String ownId) {
		// TODO Auto-generated method stub
		// 待完成
		return null;
	}

	@Override
	public int numberOfFollows(String ownId) {
		// TODO Auto-generated method stub
		// 似乎这两个都没有必要
		return 0;
	}

	@Override
	public int numberOfFans(String ownId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
