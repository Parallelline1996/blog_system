package com.blog.controller;

import java.sql.Blob;
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

import com.blog.dao.UserDao;
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
	
	// 删除标签
	@ResponseBody
	@RequestMapping(value = "/deleteTag/{tagId}")
	public int deleteTag(@PathVariable("tagId") Integer tagId) {
		if (userService.deleteTag(tagId)) {
			return 200;
		}
		else {
			return -1;
		}
	}
	
	// 测试未通过，涉及联级删除、增加的问题。待查
	// 设置标签
	@ResponseBody
	@RequestMapping("/setTag")
	public int setTag(@RequestBody BlogWithTag data) {
		
		System.out.println(data.toString());
		
		
		if(userService.setTag(data)) {
			return 200;
		}
		else {
			return -1;
		}
		
	}
	
	// 未测试，类似于上面的函数
	// 筛选标签
	@ResponseBody
	@RequestMapping("/selectTag/{tagId}")
	public List<BlogList> selectTag(@PathVariable("tagId") String tagId) {
		return null;
	}
	
	
	// 点赞
	@ResponseBody
	@RequestMapping(value = "/agree/{blogId}", method = RequestMethod.GET)
	public int agree(@PathVariable("blogId") Integer blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userService.agree(userId, blogId)) {
			return 200;
		} else {
			return -1;
		}

	}
	
	// 点踩
	@ResponseBody
	@RequestMapping(value = "/disagree/{blogId}", method = RequestMethod.GET)
	public int disagree(@PathVariable("blogId") Integer blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userService.disagree(userId, blogId)) {
			return 200;
		} else {
			return -1;
		}
	}
	
	/*
	 * 需传入: userId, nickName, eMail, phoneNumber, profile;
	*/
	// 更新用户信息
	@ResponseBody
	@RequestMapping("/updateUserData")
	public int updateUserData(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.updateUserData(user, userId);
	}
	
	
	/*
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
	}*/
	
	// 直接删除博客(可以在垃圾回收站处删除，也可以直接在文章里删除)
	@ResponseBody
	@RequestMapping(value = "/deleteBlog/{blogId}", method = RequestMethod.GET)
	public int deleteBlog(@PathVariable("blogId") Integer blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.deleteBlog(blogId, userId);
	}
	
	// 将博客放入垃圾回收站
	@ResponseBody
	@RequestMapping(value = "/deleteBlogToTrashBin/{blogId}", method = RequestMethod.GET)
	public int deleteBlogToTrashBin(@PathVariable("blogId") Integer blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.deleteBlogToTrashBin(blogId, userId);
	}
	/*
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
	*/
	
	// 撤销删除博客
	@ResponseBody
	@RequestMapping(value = "/undoDeleteBlog/{blogId}", method = RequestMethod.GET)
	public int undoDeleteBlog(@PathVariable("blogId") Integer blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.undoDeleteBlog(blogId, userId);
	}
	
	
	/*
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
	*/
	
	/*
	 * {传入的信息：
	"commentObjectId":1,
	"content":"good",
	"objectOption":1,
	"userId":1
	 * */
	// 评论
	@ResponseBody
	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public int createComment(@RequestBody Comment comment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.createComment(comment, userId);
	}
	
	
	// 目前只支持删除自己写的评论的操作
	// 删除评论
	@ResponseBody
	@RequestMapping(value = "/deleteComment/{commentId}", method = RequestMethod.GET)
	public int deleteComment(@PathVariable("commentId") Integer commentId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.deleteComment(commentId, userId);
	}
	
	
	// 你所评论过的所有评论
	@ResponseBody
	@RequestMapping("/allCommentYouMade/{page}")
	public List<Comment> allCommentYouMade(@PathVariable("page") Integer page, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.allCommentYouMade(userId, page);
	}
	
	/*
	 * 疑问：要怎么传
	 * */
	// 你所获得的所有评论
	@ResponseBody
	@RequestMapping("/allCommentYouGet/{page}")
	public List<Comment> allCommentYouGet(@PathVariable("page") Integer page, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.allCommentYouGet(userId, page);
	}
}