package com.blog.dao;

import java.util.List;

import com.blog.domain.Comment;

public interface CommentDao {
	
	boolean createComment(Comment comment);
	
	boolean deleteComment(Integer commentId);
	
	List<Comment> allCommentYouMade(Integer userId, Integer page);
	
	List<Comment> allCommentYouGet(Integer userId, Integer page);
	
	Comment findCommentById(Integer commentId);
}
