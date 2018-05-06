package com.blog.daoImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.UpDownDao;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("upDownDaoImpl")
public class UpDownDaoImpl extends HibernateUtil implements UpDownDao{

	@Override
	public int upOrDown(String userId, String blogId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean agree(String userId, String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disagree(String userId, String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

}
