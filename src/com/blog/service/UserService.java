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
	
	int createFollow(Integer ownId, Integer userId);
	
	int deleteFollow(Integer ownId, Integer userId);
	
	List<UserSimpleData> visitFollows(Integer ownId);
	
	List<UserSimpleData> visitFans(Integer ownId);
	
	int numberOfFollows(Integer ownId);
	
	int numberOfFans(Integer ownId);
	
	boolean createTag(Tag tag);
	
	boolean deleteTag(Tag tag);
	
	boolean setTag(Integer tagId, Integer blogId);
	
	List<BlogList> selectTag(Integer tagId);
	
	boolean agree(Integer userId, Integer blogId);
	
	boolean disagree(Integer userId, Integer blogId);
	
	boolean updateUserData(User user);
	
	// 在这里对数据进行转换
	boolean createBlog(Blog blog);
	
	boolean deleteBlog(Integer blogId);
	
	boolean updateBlog(Blog blog);
	
	boolean undoDeleteBlog(Integer blogId);
	
	boolean cachBlog(Blog blog);
	
	boolean createComment(Comment comment);
	
	boolean deleteComment(Integer commentId);
	
	List<Comment> allCommentYouMade(Integer userId);
	
	List<Comment> allCommentYouGet(Integer userId);
}
