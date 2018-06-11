package com.blog.daoImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public int createTag(Tag tag) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int temp = -1;
		try {
			session.save(tag);
			// 获取 Hibernate 自动为用户生成的Id
			temp = tag.getTagId();
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
	public boolean deleteTag(Tag tag) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Set<Blog> blogs = tag.getBlogs();
		boolean temp = false;
		try {
			if (blogs != null) {
				for (Blog blog : blogs) {
					blog.getTags().remove(tag);
					blogDao.updateBlog(blog);
				}
			}
			temp = delete(tag);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return temp;
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
				return update(blog);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> findTagByUserId(Integer userId) {
		String hql = "from Tag where userId = " + userId;
		Session session = sessionFactory.openSession();
		Query query = null;
		List<Tag> tags = null;
		try {
			query = session.createQuery(hql);
			tags = (List<Tag>)query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		for (Tag tag : tags) {
			tag.setBlogs(null);
		}
		return tags;
	}

}