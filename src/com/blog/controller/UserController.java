package com.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.util.request.BlogWithTag;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

@Controller
public class UserController {

	// 关注
	public int createFollow(String ownId, String userId) {
		return 100;
	}
	
	// 取消关注
	public int deleteFollow(String ownId, String userId) {
		return 100;
	}
	
	// 查看关注人的列表
	public List<UserSimpleData> visitFollows(String ownId) {
		return null;
	}
	
	// 查看粉丝的列表
	public List<UserSimpleData> visitFans(String ownId) {
		return null;
	}
	
	// 查看已关注人的总数
	public int numberOfFollows(String ownId) {
		return 0;
	}
	
	// 查看粉丝的总数
	public int numberOfFans(String ownId) {
		return 0;
	}
	
	// 创建标签
	public int createTag(Tag tag) {
		return 0;
	}
	
	// 删除标签
	public int deleteTag(Tag tag) {
		return 0;
	}
	
	// 设置标签
	public int setTag(String tagId, String blogId) {
		return 0;
	}
	
	// 筛选标签
	public List<BlogList> selectTag(String tagId) {
		return null;
	}
	
	// 点赞
	public int agree(String userId, String blogId) {
		return 0;
	}
	
	// 点踩
	public int disagree(String userId, String blogId) {
		return 0;
	}
	
	// 更新用户信息
	public int updateUserData(User user) {
		return 0;
	}
	
	// 新增博客
	public int createBlog(Blog blog) {
		return 0;
	}
	
	// 删除博客
	public int deleteBlog(String blogId) {
		return 0;
	}
	
	// 更新博客
	public int updateBlog(Blog blog) {
		return 1;
	}
	
	// 撤销删除博客
	public int undoDeleteBlog(String blogId) {
		return 0;
	}
	
	// 缓存博客
	public int cachBlog(Blog blog) {
		return 0;
	}
	
	// 评论
	public int createComment(Comment comment) {
		return 0;
	}
	
	// 删除评论
	public int deleteComment(String commentId) {
		return 0;
	}
	
	// 你所评论过的所有评论
	public List<Comment> allCommentYouMade(String userId) {
		return null;
	}
	
	// 你所获得的所有评论
	public List<Comment> allCommentYouGet(String userId) {
		return null;
	}
}
