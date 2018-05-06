package com.blog.service;

import java.util.List;

import com.blog.domain.Blog;
import com.blog.util.response.BlogList;

public interface NormalService {

	List<BlogList> readBlog();
	
	Blog findBlogById(String blogId);
}