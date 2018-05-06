package com.blog.dao;

public interface UpDownDao {
	
	int upOrDown(String userId, String blogId);
	
	boolean agree(String userId, String blogId);
	
	boolean disagree(String userId, String blogId);
}
