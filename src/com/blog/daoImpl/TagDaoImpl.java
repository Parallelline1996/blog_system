package com.blog.daoImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.TagDao;
import com.blog.domain.Tag;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("tagDaoImpl")
public class TagDaoImpl extends HibernateUtil implements TagDao {

	@Override
	public boolean createTag(Tag tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTag(Tag tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTag(String tagId, String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

}
