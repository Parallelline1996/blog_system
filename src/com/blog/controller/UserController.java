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
import com.blog.util.request.NewBlog;
import com.blog.util.request.TagList;
import com.blog.util.response.BlogList;
import com.blog.util.response.BlogListDataNew;
import com.blog.util.response.UserSimpleData;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 关注函数，添加关注
	 * @param userId 操作人的id，即用户id
	 * @return 返回一个整数类型，404 代表未登陆无法关注，200 代表创建成功，-1 代表已关注
	 */
	@ResponseBody
	@RequestMapping(value = "/createFollow/{userId}", method = RequestMethod.GET)
	public int createFollow(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer fanId = (Integer)session.getAttribute("userId");
		if (fanId == null) {
			return 404;
		}
		return userService.createFollow(fanId, userId); 
	}
	
	/**
	 * 取消关注函数
	 * @param userId 操作人的id，即用户id
	 * @return 返回一个整数类型，404代表未登陆无法进行操作，200 代表取消关注成功，-1 代表实际上并未关注
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteFollow/{userId}", method = RequestMethod.GET)
	public int deleteFollow(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		if (ownId == null) {
			return 404;
		}
		return userService.deleteFollow(ownId, userId);
	}
	
	/**
	 * 查看关注人列表
	 * @return 用户简单信息列表
	 */
	@ResponseBody
	@RequestMapping(value = "/visitFollows", method = RequestMethod.GET)
	public List<UserSimpleData> visitFollows() {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.visitFollows(ownId);
	}
	
	/**
	 * 查看粉丝列表
	 * @return 用户简单信息列表
	 */
	@ResponseBody
	@RequestMapping(value = "/visitFans", method = RequestMethod.GET)
	public List<UserSimpleData> visitFans() {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.visitFans(ownId);
	}
	
	/**
	 * 查看已经关注人数
	 * @return 整数类型，负数代表操作失败，非负数代表人数
	 */
	@ResponseBody
	@RequestMapping(value = "/numberOfFollows", method = RequestMethod.GET)
	public int numberOfFollows() {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.numberOfFollows(ownId);
	}
	
	/**
	 * 查看粉丝人数 
	 * @return 整数类型，负数代表操作失败，非负数代表人数
	 */
	@ResponseBody
	@RequestMapping(value = "/numberOfFans", method = RequestMethod.GET)
	public int numberOfFans() {
		HttpSession session = request.getSession();
		Integer ownId = (Integer)session.getAttribute("userId");
		return userService.numberOfFans(ownId);
	}
	
	/**
	 * 创建标签
	 * @param tag 批量标签内容
	 * @return list，标签插入到数据库中，对应的标签Id的list
	 */
	@ResponseBody
	@RequestMapping(value = "/createTag", method = RequestMethod.POST)
	public List<Integer> createTag(@RequestBody TagList tag) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			return null;
		}
		return userService.createTag(tag, userId);
	}
	
	/**
	 * 删除标签
	 * @param tagId 要删除标签的id
	 * @return 整数类型，200 代表成功，-1 代表错误
	 */
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
	
	/**
	 * 设置标签
	 * @param data 设置标签的博客id和标签id
	 * @return 整数类型，200 代表成功，-1 代表失败
	 */
	@ResponseBody
	@RequestMapping("/setTag")
	public int setTag(@RequestBody BlogWithTag data) {
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
	public List<BlogList> selectTag(@PathVariable("tagId") Integer tagId) {
		return null;
	}
	
	/**
	 * 根据用户查询标签
	 * @return 标签list
	 */
	@ResponseBody
	@RequestMapping("/tagById")
	public List<Tag> selectTagByUserId() {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.selectTagById(userId);
	}
	
	/**
	 * 根据博客Id查询标签
	 * @param blogId 博客id
	 * @return 标签list
	 */
	@ResponseBody
	@RequestMapping("/tagByBlogId/{blogId}")
	public List<Tag> selectTagByBlogId(@PathVariable("blogId") Integer blogId) {
		return userService.selectTagByBlog(blogId);
	}
	
	/**
	 * 点赞
	 * @param blogId 博客Id
	 * @return 整数类型，200 代表成功，-1 代表失败
	 */
	@ResponseBody
	@RequestMapping(value = "/agree/{blogId}", method = RequestMethod.GET)
	public int agree(@PathVariable("blogId") Integer blogId) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userService.agree(userId, blogId)) {
			return 200;
		} else {
			return -1;
		}

	}
	
	/**
	 * 点踩
	 * @param blogId 博客id
	 * @return 整数类型，200 代表成功，-1 代表失败
	 */
	@ResponseBody
	@RequestMapping(value = "/disagree/{blogId}", method = RequestMethod.GET)
	public int disagree(@PathVariable("blogId") Integer blogId) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userService.disagree(userId, blogId)) {
			return 200;
		} else {
			return -1;
		}
	}
	
	/**
	 * 更新用户信息
	 * @param user 包括：userId, password, nickName, eMail, phoneNumber, profile
	 * @return 整数类型，200 代表修改成功，-1 代表对非本人的数据进行修改，-2 代表更新错误，-3 代表密码错误
	 */
	@ResponseBody
	@RequestMapping("/updateUserData")
	public int updateUserData(@RequestBody User user) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.updateUserData(user, userId);
	}
	
	/**
	 * 新增博客 
	 * @param newBlog：包括blogTitle, blogContent, tags
	 * @return 整数类型，200 代表成功，-1 代表失败
	 */
	@ResponseBody
	@RequestMapping("/createBlog")
	public int createBlog(@RequestBody NewBlog newBlog) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userService.createBlog(newBlog, userId)){
			return 200;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * 删除博客（可以在垃圾回收站处删除，也可以直接在文章里删除）
	 * @param blogId 博客id
	 * @return 整数类型，200 代表成功，-1 代表非本人操作，-2 代表操作错误
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBlog/{blogId}", method = RequestMethod.GET)
	public int deleteBlog(@PathVariable("blogId") Integer blogId) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.deleteBlog(blogId, userId);
	}
	
	/**
	 * 将博客放入垃圾回收站
	 * @param blogId 博客id
	 * @return 整数类型，200 代表成功，-1 代表非本人操作，-2 代表操作错误
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBlogToTrashBin/{blogId}", method = RequestMethod.GET)
	public int deleteBlogToTrashBin(@PathVariable("blogId") Integer blogId) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.deleteBlogToTrashBin(blogId, userId);
	}
	
	/**
	 * 更新博客
	 * @param blog 博客信息
	 * @return 整数类型，200 代表成功，-1 代表非本人操作，-2 代表操作错误
	 */
	@ResponseBody
	@RequestMapping("/updateBlog")
	public int updateBlog(@RequestBody NewBlog blog) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.updateBlog(blog, userId);
	}
	
	/**
	 * 撤销删除博客
	 * @param blogId 博客Id
	 * @return 整数类型，200 代表成功，-1 代表非本人操作，-2 代表操作错误
	 */
	@ResponseBody
	@RequestMapping(value = "/undoDeleteBlog/{blogId}", method = RequestMethod.GET)
	public int undoDeleteBlog(@PathVariable("blogId") Integer blogId) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.undoDeleteBlog(blogId, userId);
	}
	
	// 发表缓存博客
	// 未完成
	@ResponseBody
	@RequestMapping(value = "/publishBlog")
	public int publishBlog(@RequestBody NewBlog blog) {
		//HttpSession session = request.getSession();
		//Integer userId = (Integer)session.getAttribute("userId");
		return 200;
	}
	
	// 未完成
	// 缓存博客
	@ResponseBody
	@RequestMapping("/cachBlog")
	public int cachBlog(@RequestBody NewBlog blog) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.cachBlog(blog, userId);
	}
	
	/**
	 * 发表评论
	 * @param comment 评论的信息，包括：commentObjectId, content, objectOption, userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public int createComment(@RequestBody Comment comment) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.createComment(comment, userId);
	}
	
	
	// 目前只支持删除自己写的评论的操作
	/**
	 * 删除自己写的评论
	 * @param commentId 评论id
 	 * @return 整数类型，200 代表成功，-1 代表非本人操作，-2 代表操作错误
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteComment/{commentId}", method = RequestMethod.GET)
	public int deleteComment(@PathVariable("commentId") Integer commentId) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.deleteComment(commentId, userId);
	}
	
	
	/**
	 * 分页展示用户所写的所有评论
	 * @param page 页码
	 * @return 评论List
	 */
	@ResponseBody
	@RequestMapping(value = "/allCommentYouMade/{page}", method = RequestMethod.GET)
	public List<Comment> allCommentYouMade(@PathVariable("page") Integer page) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.allCommentYouMade(userId, page);
	}
	
	/**
	 * 分页根据博客显示评论
	 * @param page 页码
	 * @param blog 博客信息，主要是拿其id
	 * @return 评论List
	 */
	@ResponseBody
	@RequestMapping(value = "/commentByBlog/{page}", method = RequestMethod.POST)
	public List<Comment> commentByBlog(@PathVariable("page") Integer page, @RequestBody Blog blog) {
		return userService.commentByBlog(blog.getBlogId(), page);
	}
	
	/**
	 * 你所评论过的所有评论的数目
	 * @return 整数类型，-1 代表未登录，非负整数代表数目
	 */
	@ResponseBody
	@RequestMapping(value = "/theNumberOfCommentYouMake", method = RequestMethod.GET)
	public int theNumberAllCommentYouMade() {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.numberOfCommentYouMade(userId);
	}
	
	
	// 未完成
	// 你所获得的所有评论
	@ResponseBody
	@RequestMapping("/allCommentYouGet/{page}")
	public List<Comment> allCommentYouGet(@PathVariable("page") Integer page) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.allCommentYouGet(userId, page);
	}
	
	/**
	 * 分页查看用户个人博客
	 * @param page 页面
	 * @return 博客List
	 */
	@ResponseBody
	@RequestMapping(value = "/blog/{page}", method = RequestMethod.GET)
	public List<BlogList> blog(@PathVariable("page") Integer page) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.blog(userId, page);
	}
	
	/**
	 * 分页查看草稿箱列表
	 * @param page 页码
	 * @return 博客List
	 */
	@ResponseBody
	@RequestMapping(value = "/cachBlog/{page}", method = RequestMethod.GET)
	public List<BlogList> cachBlog(@PathVariable("page") Integer page) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.cachBlog(userId, page);
	}
	
	/**
	 * 分页查看垃圾箱博客列表
	 * @param page 页码
	 * @return 博客list
	 */
	@ResponseBody
	@RequestMapping(value = "/trashBinBlog/{page}", method = RequestMethod.GET)
	public List<BlogList> trashBinBlog(@PathVariable("page") Integer page) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.trashBinBlog(userId, page);
	}
	
	/**
	 * 查看回收站的博客数目
	 * @param userId 用户id
	 * @return 返回整数类型，-1 代表错误，非负代表数目
	 */
	@ResponseBody
	@RequestMapping(value = "/numberOfTrashBinBlog", method = RequestMethod.GET)
	public int numberOfTrashBinBlog() {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.numberOfTrasnBinBlog(userId);
	}
	
	/**
	 * 查看普通博客数目
	 * @param userId 用户id
	 * @return 返回整数类型，-1 代表错误，非负代表数目
	 */
	@ResponseBody
	@RequestMapping(value = "/numberOfBlog", method = RequestMethod.GET)
	public int numberOfBlog() {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.numberOfBlog(userId);
	}
	
	/**
	 * 获取用户个人信息
	 * @return 用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserData", method = RequestMethod.GET)
	public User getUserData() {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		return userService.getUserData(userId);
	}
	
	/**
	 * 分页查看他人的博客列表
	 * @param page 页码
	 * @param user 用于输入被查询者的用户id
	 * @return list，信息列表
	 */
	@ResponseBody
	@RequestMapping(value = "/visitOthersBlog/{page}", method = RequestMethod.POST)
	public List<BlogListDataNew> visitOthersBlog(@PathVariable("page") Integer page, @RequestBody User user) {
		Integer userId = user.getUserId();
		return userService.visitOthersBlog(userId, page);
	}
}