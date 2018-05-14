package com.blog.daoImpl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.BlogDao;
import com.blog.dao.TagDao;
import com.blog.domain.Blog;
import com.blog.domain.Tag;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("tagDaoImpl")
public class TagDaoImpl extends HibernateUtil implements TagDao {

	@Autowired
	@Qualifier("blogDaoImpl")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createTag(Tag tag) {
		return save(tag);
	}

	@Override
	public boolean deleteTag(Tag tag) {
		return delete(tag);
	}

	@Override
	public boolean setTag(Integer tagId, Integer blogId) {
		Blog blog = blogDao.findBlogById(blogId);
		if(blog!=null) {
			Set<Tag> temp = blog.getTags();
			Tag tag = findTagById(tagId);
			if(tag!=null) {
				temp.add(tag);
				blog.setTags(temp);
				return true;
			}
		}
		return false;
	}

	@Override
	public Tag findTagById(Integer tagId) {
		Session session = sessionFactory.openSession();
		Tag tag = null;
		try {
			tag = (Tag)session.get(Tag.class, tagId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tag;
	}

}