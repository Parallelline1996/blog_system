package com.blog.service;

import java.util.List;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.util.request.BlogWithTag;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

public interface UserService {
	
	int createFollow(String ownId, String userId);
	
	int deleteFollow(String ownId, String userId);
	
	List<UserSimpleData> visitFollows(String ownId);
	
	List<UserSimpleData> visitFans(String ownId);
	
	int numberOfFollows(String ownId);
	
	int numberOfFans(String ownId);
	
	boolean createTag(Tag tag);
	
	boolean deleteTag(Tag tag);
	
	boolean setTag(String tagId, String blogId);
	
	List<BlogList> selectTag(String tagId);
	
	boolean agree(String userId, String blogId);
	
	boolean disagree(String userId, String blogId);
	
	boolean updateUserData(User user);
	
	// 在这里对数据进行转换
	boolean createBlog(Blog blog);
	
	boolean deleteBlog(String blogId);
	
	boolean updateBlog(Blog blog);
	
	boolean undoDeleteBlog(String blogId);
	
	boolean cachBlog(Blog blog);
	
	boolean createComment(Comment comment);
	
	boolean deleteComment(String commentId);
	
	List<Comment> allCommentYouMade(String userId);
	
	List<Comment> allCommentYouGet(String userId);
}
