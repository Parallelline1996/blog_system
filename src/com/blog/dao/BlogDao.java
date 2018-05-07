package com.blog.dao;

import java.util.List;

import com.blog.domain.Blog;
import com.blog.util.response.BlogList;

public interface BlogDao {

	boolean createBlog(Blog blog);
	
	boolean deleteBlog(String blogId);
	
	boolean deleteBlogToTrashBin(String blogId);
	
	boolean updateBlog(Blog blog);
	
	Blog findBlogById(String blogId);
	
	boolean undoDeleteBlog(String blogId);
	
	boolean cachBlog(Blog blog);
	
	List<Blog> allBlog();
	
	List<Blog> allBlogById(String userId);
	
	List<Blog> listPageAllBlog(int pageNo, int pageNum);
	
	List<Blog> listPageAllBlogById(int pageNo, int pageNum);
	
	List<BlogList> selectTag(String tagId);
}
