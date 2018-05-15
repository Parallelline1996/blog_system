package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	// 关注
	@ResponseBody
	@RequestMapping(value = "/createFollow/{userId}", method = RequestMethod.GET)
	public int createFollow(@PathVariable("userId") Integer userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		if (ownId == null) {
			return 404;
		}
		return userService.createFollow(ownId, userId); 
	}
	
	// 取消关注
	@ResponseBody
	@RequestMapping(value = "/deleteFollow/{userId}", method = RequestMethod.GET)
	public int deleteFollow(@PathVariable("userId") Integer userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		if (ownId == null) {
			return 404;
		}
		return userService.deleteFollow(ownId, userId);
	}
	
	// 查看关注人的列表
	@ResponseBody
	@RequestMapping(value = "/visitFollows", method = RequestMethod.GET)
	public List<UserSimpleData> visitFollows(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.visitFollows(ownId);
	}
	
	// 查看粉丝的列表
	@ResponseBody
	@RequestMapping(value = "/visitFans", method = RequestMethod.GET)
	public List<UserSimpleData> visitFans(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.visitFans(ownId);
	}
	
	// 查看已关注人的总数
	@ResponseBody
	@RequestMapping(value = "/numberOfFollows", method = RequestMethod.GET)
	public int numberOfFollows(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.numberOfFollows(ownId);
	}
	
	// 查看粉丝的总数
	@ResponseBody
	@RequestMapping(value = "/numberOfFans", method = RequestMethod.GET)
	public int numberOfFans(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.numberOfFans(ownId);
	}
	
	// 创建标签，只需要上传标签的内容即可
	@ResponseBody
	@RequestMapping(value = "/createTag", method = RequestMethod.POST)
	public int createTag(@RequestBody Tag tag, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userService.createTag(tag, userId)) {
			return 200;
		} else {
			return -1;
		}
	}
	/*
	// 删除标签
	@ResponseBody
	@RequestMapping(value = "/deleteTag")
	public int deleteTag(@RequestBody Tag tag) {
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
		return 0;
	}
	
	// 筛选标签
	@ResponseBody
	@RequestMapping("/selectTag/{tagId}")//不通过
	public List<BlogList> selectTag(@PathVariable("tagId") String tagId) {
		//return userService.selectTag(tagId);
		return null;
	}
	
	/*
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