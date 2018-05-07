package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.util.HibernateUtil;
import com.blog.util.response.BlogList;


@Repository
@Qualifier("blogDaoImpl")
public class BlogDaoImpl extends HibernateUtil implements BlogDao {

	@Autowired
	@Qualifier("sesstionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createBlog(Blog blog) {
		return save(blog);
	}

	@Override
	public boolean deleteBlog(String blogId) {
		Blog blog = findBlogById(blogId);
		blog.setBlogState(2);
		return true;
	}

	@Override
	public boolean deleteBlogToTrashBin(String blogId) {
		Blog blog = findBlogById(blogId);
		// blogState: 0->正常  1->垃圾箱  2->彻底删除 
		blog.setBlogState(1);
		return true;
	}
	
	@Override
	public boolean updateBlog(Blog blog) {
		return update(blog);
	}

	@Override
	public Blog findBlogById(String blogId) {
		Blog blog = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			blog = (Blog) session.get(Blog.class, blogId);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return blog;
	}

	@Override
	public boolean undoDeleteBlog(String blogId) {
		Blog blog = findBlogById(blogId);
		blog.setBlogState(0);
		return false;
	}

	@Override
	public boolean cachBlog(Blog blog) {
		// -1表示该博客未完成
		blog.setBlogState(-1);
		return save(blog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> allBlog() {
		String hql = "form blog";
		return (List<Blog>) findByHql(hql, null);
	}

	@Override
	public List<Blog> allBlogById(String userId) {
		return null;
	}

	@Override
	public List<Blog> listPageAllBlog(int pageNo, int pageNum) {
		String hql = "form blog";
		List<Object> temp = null;
		temp = listpage(hql, pageNo, pageNum);
		List<Blog> blogs = new ArrayList<>();
		for (Object object : temp) {
			blogs.add((Blog)object);
		}
		return blogs;
	}

	@Override
	public List<Blog> listPageAllBlogById(int pageNo, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogList> selectTag(String tagId) {
		// TODO Auto-generated method stub
		return null;
	}

}
