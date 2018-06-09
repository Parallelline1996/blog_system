package com.blog.service;

import java.util.List;

import com.blog.util.response.BlogData;
import com.blog.util.response.BlogList;

public interface NormalService {

	/**
	 * 返回博客简单信息列表
	 * @return List
	 */
	List<BlogList> readBlog();
	
	/**
	 * 分页返回博客简单信息列表
	 * @param page
	 * @return List
	 */ 
	List<BlogList> readBlogByPage(int page);

	/**
	 * 通过博客id查询博客内容
	 * @param blogId
	 * @return 博客信息
	 */
	BlogData findBlogById(Integer blogId);
}