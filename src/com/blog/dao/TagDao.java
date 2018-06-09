package com.blog.dao;

import java.util.List;

import com.blog.domain.Tag;

public interface TagDao {

	/**
	 * 创建标签
	 * @param tag 标签内容
	 * @return 整数类型，-1 代表失败，非负整数表示tagId
	 */
	int createTag(Tag tag);
	
	/**
	 * 删除标签
	 * @param tag 标签内容
	 * @return boolean
	 */
	boolean deleteTag(Tag tag);
	
	/**
	 * 设置标签
	 * @param tagId 标签id
	 * @param blogId 博客id
	 * @return boolean 
	 */
	boolean setTag(Integer tagId, Integer blogId);
	
	/**
	 * 通过标签Id查看标签内容
	 * @param tagId 标签内容
	 * @return tag 标签对象
	 */
	Tag findTagById(Integer tagId);
	
	/**
	 * 通过用户id查看标签，即用户所设定的所有标签
	 * @param userId 用户Id
	 * @return List 用户所设置的标签
	 */
	List<Tag> findTagByUserId(Integer userId);
}
