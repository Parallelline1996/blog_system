package com.blog.dao;


import com.blog.domain.Tag;

public interface TagDao {

	boolean createTag(Tag tag);
	
	boolean deleteTag(Tag tag);
	
	boolean setTag(String tagId, String blogId);
	
	Tag findTagById(String tagId);
}
