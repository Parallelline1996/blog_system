package com.blog.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.FollowDao;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("follwoDaoImpl")
public class FollowDaoImpl extends HibernateUtil implements FollowDao{

	@Override
	public boolean createFollow(String ownId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFollow(String ownId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> visitFans(String ownId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> visitFollow(String ownId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfFollows(String ownId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numberOfFans(String ownId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
