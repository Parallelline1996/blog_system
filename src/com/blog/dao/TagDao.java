package com.blog.dao;


import java.util.List;

import com.blog.domain.Tag;

public interface TagDao {

	int createTag(Tag tag);
	
	boolean deleteTag(Tag tag);
	
	boolean setTag(Integer tagId, Integer blogId);
	
	Tag findTagById(Integer tagId);
	
	List<Tag> findTagByUserId(Integer userId);
}
