package com.blog.dao;

import java.util.List;


public interface FollowDao {

	boolean createFollow(Integer ownId, Integer userId);
	
	boolean deleteFollow(Integer ownId, Integer userId);
	
	boolean existFollow(Integer ownId, Integer userId);
	
	List<Integer> visitFans(Integer ownId);
	
	List<Integer> visitFollow(Integer ownId);
}
