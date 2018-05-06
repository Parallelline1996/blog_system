package com.blog.dao;

import java.util.List;


public interface FollowDao {

	boolean createFollow(String ownId, String userId);
	
	boolean deleteFollow(String ownId, String userId);
	
	List<String> visitFans(String ownId);
	
	List<String> visitFollow(String ownId);
	
	int numberOfFollows(String ownId);
	
	int numberOfFans(String ownId);
}
