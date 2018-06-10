package com.blog.dao;

import java.util.List;

import com.blog.domain.Comment;

public interface CommentDao {
	
	/**
	 * 创建评论
	 * @param comment 评论的内容
	 * @return boolean
	 */
	boolean createComment(Comment comment);
	
	/**
	 * 删除评论
	 * @param commentId 被删除评论的id
	 * @return boolean
	 */
	boolean deleteComment(Integer commentId);
	
	/**
	 * 统计用户所做过评论的数目
	 * @param userId
	 * @return 整数类型，返回数目
	 */
	int numberOfCommentYouMade(Integer userId);
	
	/**
	 * 分页返回用户所做过的评论
	 * @param userId 用户id
	 * @param page 第几页
	 * @return
	 */
	List<Comment> allCommentYouMade(Integer userId, Integer page);
	
	// 未完成
	List<Comment> allCommentYouGet(Integer userId, Integer page);
	
	/**
	 * 通过评论id查看评论
	 * @param commentId
	 * @return
	 */
	Comment findCommentById(Integer commentId);
	
	/**
	 * 分页展示博客id查看评论
	 * @param blogId 博客id
	 * @param page 页码
	 * @return 评论的List
	 */
	List<Comment> findCommentByBlog(Integer blogId, Integer page);
	
	/**
	 * 查看博客对应的评论数
	 * @param blogId
	 * @return 整数类型，返回评论数目
	 */
	int numberOfCommentsByBlog(Integer blogId);
}
