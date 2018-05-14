package com.blog.dao;

import java.util.List;


public interface FollowDao {

	boolean createFollow(Integer ownId, Integer userId);
	
	boolean deleteFollow(Integer ownId, Integer userId);
	
	List<String> visitFans(Integer ownId);
	
	List<String> visitFollow(Integer ownId);
	
	int numberOfFollows(Integer ownId);
	
	int numberOfFans(Integer ownId);
}
