package com.blog.dao;

import java.util.List;

public interface FollowDao {

	/**
	 * 新建关注
	 * @param fansId 用户的id
	 * @param userId 用户要关注人的id
	 * @return boolean
	 */
	boolean createFollow(Integer fansId, Integer userId);
	
	/**
	 * 删除关注
	 * @param fansId 用户的id
	 * @param userId 用户所关注人的id
	 * @return boolean
	 */
	boolean deleteFollow(Integer fansId, Integer userId);
	
	/**
	 * 判断是否已经关注
	 * @param fansId 用户id
	 * @param userId 用户要关注人的Id
	 * @return boolean
	 */
	boolean existFollow(Integer fansId, Integer userId);
	
	/**
	 * 查看用户的所有粉丝
	 * @param ownId 用户Id
	 * @return 返回粉丝list
	 */
	List<Integer> visitFans(Integer ownId);
	
	/**
	 * 查看用户的所有关注人
	 * @param ownId 用户Id
	 * @return 返回关注人list
	 */
	List<Integer> visitFollow(Integer ownId);
}
