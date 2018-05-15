package com.blog.dao;

import java.util.List;

import com.blog.domain.Blog;
import com.blog.util.response.BlogList;

public interface BlogDao {

	boolean createBlog(Blog blog);
	
	boolean deleteBlog(Integer blogId);
	
	boolean deleteBlogToTrashBin(Integer blogId);
	
	boolean updateBlog(Blog blog);
	
	Blog findBlogById(Integer blogId);
	
	boolean undoDeleteBlog(Integer blogId);
	
	boolean cachBlog(Blog blog);
	
	List<Blog> allBlog();
	
	List<Blog> allBlogByPage(int page);
	
	List<Blog> allBlogById(Integer userId);
	
	List<Blog> listPageAllBlog(int pageNo, int pageNum);
	
	List<Blog> listPageAllBlogById(int pageNo, int pageNum);
	
	List<BlogList> selectTag(Blog blog, Integer tagId);
}
