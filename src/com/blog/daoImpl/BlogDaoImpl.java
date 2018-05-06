package com.blog.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.util.HibernateUtil;
import com.blog.util.response.BlogList;

@Repository
@Qualifier("blogDaoImpl")
public class BlogDaoImpl extends HibernateUtil implements BlogDao {

	@Override
	public boolean createBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBlog(String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Blog findBlogById(String blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean undoDeleteBlog(String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cachBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Blog> allBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogList> selectTag(String tagId) {
		// TODO Auto-generated method stub
		return null;
	}

}
