package com.blog.dao;

public interface UpDownDao {
	
	/**
	 * 判断用户是否对该博客进行点赞或点踩操作
	 * @param userId 用户id
	 * @param blogId 博客id
	 * @return 整数类型，1 表示赞，-1 表示踩，0 表示未进行任何操作
	 */
	int upOrDown(Integer userId, Integer blogId);
	
	/**
	 * 点赞操作
	 * @param userId 用户Id
	 * @param blogId 博客id
	 * @return boolean
	 */
	boolean agree(Integer userId, Integer blogId);
	
	/**
	 * 点踩操作
	 * @param userId 用户Id
	 * @param blogId 博客id
	 * @return boolean
	 */
	boolean disagree(Integer userId, Integer blogId);
}
