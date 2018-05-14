package com.blog.dao;


import com.blog.domain.Tag;

public interface TagDao {

	boolean createTag(Tag tag);
	
	boolean deleteTag(Tag tag);
	
	boolean setTag(Integer tagId, Integer blogId);
	
	Tag findTagById(Integer tagId);
}
