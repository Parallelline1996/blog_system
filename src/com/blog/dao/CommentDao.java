package com.blog.dao;

import java.util.List;

import com.blog.domain.Comment;

public interface CommentDao {
	
	boolean createComment(Comment comment);
	
	boolean deleteComment(String commentId);
	
	List<Comment> allCommentYouMade(String userId);
	
	List<Comment> allCommentYouGet(String userId);
}
