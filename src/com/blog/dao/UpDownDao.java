package com.blog.dao;

public interface UpDownDao {
	
	int upOrDown(Integer userId, Integer blogId);
	
	boolean agree(Integer userId, Integer blogId);
	
	boolean disagree(Integer userId, Integer blogId);
}
