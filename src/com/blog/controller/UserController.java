package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.service.UserService;
import com.blog.util.request.BlogWithTag;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	/*
	// 关注
	@ResponseBody
	@RequestMapping("/createFollow")
	public int createFollow(@RequestBody String ownId, String userId) {//不通过
		return userService.createFollow(ownId, userId); 
		//return 100;
	}
	
	// 取消关注
	@ResponseBody
	@RequestMapping("/deleteFollow")
	public int deleteFollow(@RequestBody String ownId, String userId) {//不通过
		return userService.deleteFollow(ownId, userId);
		//return 100;
	}
	
	// 查看关注人的列表
	@ResponseBody
	@RequestMapping("/visitFollows/{ownId}")
	public List<UserSimpleData> visitFollows(@PathVariable("ownId") String ownId) {//不通过
		return userService.visitFollows(ownId);
		//return null;
	}
	
	// 查看粉丝的列表
	@ResponseBody
	@RequestMapping("/visitFans/{ownId}")
	public List<UserSimpleData> visitFans(@PathVariable("ownId") String ownId) {//不通过
		return userService.visitFans(ownId);
		//return null;
	}
	
	// 查看已关注人的总数
	@ResponseBody
	@RequestMapping("/numberOfFollows/{ownId}")
	public int numberOfFollows(@PathVariable("ownId") String ownId) {//通过
		return userService.numberOfFollows(ownId);
		//return 0;
	}
	
	// 查看粉丝的总数
	@ResponseBody
	@RequestMapping("/numberOfFans/{ownId}")
	public int numberOfFans(@PathVariable("ownId") String ownId) {//通过
		return userService.numberOfFans(ownId);
		//return 0;
	}
	
	// 创建标签
	@ResponseBody
	@RequestMapping("/createTag")
	public int createTag(@RequestBody Tag tag) {//不通过
		if( userService.createTag(tag)){
			return 200;
		}
		else{
			return -1;
		}
		//return 0;
	}
	
	// 删除标签
	@ResponseBody
	@RequestMapping("/deleteTag")
	public int deleteTag(@RequestBody Tag tag) {//不通过
		if(userService.deleteTag(tag)) {
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 设置标签
	@ResponseBody
	@RequestMapping("/setTag")
	public int setTag(@RequestBody String tagId, String blogId) {//不通过
		if(userService.setTag(tagId, blogId)) {
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 筛选标签
	@ResponseBody
	@RequestMapping("/selectTag/{tagId}")//不通过
	public List<BlogList> selectTag(@PathVariable("tagId") String tagId) {
		return userService.selectTag(tagId);
	}
	
	// 点赞
	@ResponseBody
	@RequestMapping("/agree")//不通过,逻辑理不清
	public int agree(@RequestBody String userId, String blogId) {
		if( userService.agree(userId, blogId)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 点踩
	@ResponseBody
	@RequestMapping("/disagree")//不通过,逻辑理不清
	public int disagree(@RequestBody String userId, String blogId) {
		if( userService.disagree(userId, blogId)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 更新用户信息
	@ResponseBody
	@RequestMapping("/updateUserData")
	public int updateUserData(@RequestBody User user) {//通过
	
		if( userService.updateUserData(user)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 新增博客
	@ResponseBody
	@RequestMapping("/createBlog")
	public int createBlog(@RequestBody Blog blog) {//不通过
		if( userService.createBlog(blog)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 删除博客
	@ResponseBody
	@RequestMapping("/deleteBlog/{blogId}")
	public int deleteBlog(@PathVariable("blogId") String blogId) {//通过
		if(userService.deleteBlog(blogId)) {
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 更新博客
	@ResponseBody
	@RequestMapping("/updateBlog")//不通过
	public int updateBlog(@RequestBody Blog blog) {
		if( userService.updateBlog(blog)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 撤销删除博客
	@ResponseBody
	@RequestMapping("/undoDeleteBlog/{blogId}")//通过
	public int undoDeleteBlog(@PathVariable("blogId") String blogId) {
		if(userService.undoDeleteBlog(blogId)) {
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 缓存博客
	@ResponseBody
	@RequestMapping("/cachBlog")//不通过
	public int cachBlog(@RequestBody Blog blog) {
		if( userService.cachBlog(blog)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 评论
	@ResponseBody
	@RequestMapping("/createComment")//不通过
	public int createComment(@RequestBody Comment comment) {
		if( userService.createComment(comment)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 删除评论
	@ResponseBody
	@RequestMapping("/deleteComment/{commentId}")//通过
	public int deleteComment(@PathVariable("commentId") String commentId) {
		if( userService.deleteComment(commentId)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 你所评论过的所有评论
	@ResponseBody
	@RequestMapping("/allCommentYouMade/{userId}")//不通过
	public List<Comment> allCommentYouMade(@PathVariable("userId") String userId) {
		return userService.allCommentYouMade(userId);
	}
	
	// 你所获得的所有评论
	@ResponseBody
	@RequestMapping("/allCommentYouGet/{userId}")//不通过
	public List<Comment> allCommentYouGet(@PathVariable("userId")  String userId) {
		return userService.allCommentYouGet(userId);
	}*/
}