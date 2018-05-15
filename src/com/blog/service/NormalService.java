package com.blog.service;

import java.util.List;

import com.blog.util.response.BlogData;
import com.blog.util.response.BlogList;

public interface NormalService {

	List<BlogList> readBlog();
	
	List<BlogList> readBlogByPage(int page);

	BlogData findBlogById(Integer blogId);
}