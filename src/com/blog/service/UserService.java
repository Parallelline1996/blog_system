package com.blog.service;

import java.util.List;

import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.util.request.BlogWithTag;
import com.blog.util.request.NewBlog;
import com.blog.util.request.TagList;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

public interface UserService {
	
	/**
	 * 创建关注
	 * @param fanId 执行操作人id，即粉丝的id
	 * @param userId 被关注者的Id
	 * @return 整数类型 200 代表成功，-1 代表已关注，-2 代表系统异常
	 */
	int createFollow(Integer fanId, Integer userId);
	
	/**
	 * 取消关注
	 * @param fanId 执行操作人id，即粉丝的id
	 * @param userId 被关注人的id
	 * @return 整数类型，200 代表成功，-1 代表未关注，-2 代表系统异常
	 */
	int deleteFollow(Integer fanId, Integer userId);
	
	/**
	 * 查看已经关注用户的列表
	 * @param ownId 用户id
	 * @return List，用户简单信息列表
	 */
	List<UserSimpleData> visitFollows(Integer ownId);
	
	/**
	 * 查看所有粉丝的列表
	 * @param ownId 用户id
	 * @return list，用户简单信息列表
	 */
	List<UserSimpleData> visitFans(Integer ownId);
	
	/**
	 * 查看已关注人数
	 * @param ownId 用户id
	 * @return 整数类型，已经关注的人数
	 */
	int numberOfFollows(Integer ownId);
	
	/**
	 * 查看粉丝数目
	 * @param ownId 用户id
	 * @return 整数类型，粉丝的数目
	 */
	int numberOfFans(Integer ownId);
	
	/**
	 * 创建标签
	 * @param tags 标签信息列表
	 * @param userId 用户id
	 * @return list，返回插入到数据库后，每一个标签的标签id
	 */
	List<Integer> createTag(TagList tags, Integer userId);
	
	/**
	 * 删除标签
	 * @param tagId 标签id
	 * @return boolean
	 */
	boolean deleteTag(Integer tagId);
	
	/**
	 * 为博客设置标签
	 * @param data 博客与标签信息
	 * @return boolean
	 */
	boolean setTag(BlogWithTag data);
	
	// 未完成
	List<BlogList> selectTag(Integer tagId);
	
	/**
	 * 查询用户已设置的标签列表
	 * @param userId 用户id
	 * @return list，标签列表
	 */
	List<Tag> selectTagById(Integer userId);
	
	/**
	 * 查询博客被设置的标签列表
	 * @param blogId 博客id
	 * @return list，标签列表
	 */
	List<Tag> selectTagByBlog(Integer blogId);
	
	/**
	 * 点赞
	 * @param userId 用户Id
	 * @param blogId 要进行点赞的博客id
	 * @return boolean
	 */
	boolean agree(Integer userId, Integer blogId);
	
	/**
	 * 点踩
	 * @param userId 用户id
	 * @param blogId 博客id
	 * @return boolean
	 */
	boolean disagree(Integer userId, Integer blogId);
	
	/**
	 * 更新用户个人信息
	 * @param user 用户信息
	 * @param userId 用户id，用于验证是否为本人操作
	 * @return 整数类型，200 代表成功，-1 修改了非本人的信息，-2 更新错误，-3 密码错误
	 */
	int updateUserData(User user, Integer userId);
	
	/**
	 * 新建博客
	 * @param blog 博客信息
	 * @param userId 新建博客的用户id
	 * @return boolean
	 */
	boolean createBlog(NewBlog blog, Integer userId);
	
	/**
	 * 删除博客
	 * @param blogId 博客id
	 * @param userId 用户id
	 * @return 整数类型，200 成功，-1 博客已被删除或非本人操作，-2 系统异常
	 */
	int deleteBlog(Integer blogId, Integer userId);
	
	/**
	 * 将博客丢进回收站
	 * @param blogId 博客id
	 * @param userId 用户id
	 * @return 整数类型，200 成功，-1 博客不存在或非本人操作，-2 系统异常
	 */
	int deleteBlogToTrashBin(Integer blogId, Integer userId);
	
	/**
	 * 更新博客
	 * @param blog 博客信息
	 * @param userId 用户Id
	 * @return 整数类型，200 成功，-1 非本人操作，-2 系统异常
	 */
	int updateBlog(NewBlog blog, Integer userId);
	
	/**
	 * 撤销删除博客，即从垃圾箱中恢复博客
	 * @param blogId 博客id
	 * @param userId 用户id
	 * @return 整数类型，200 成功，-1 非本人操作，-2 系统异常
	 */
	int undoDeleteBlog(Integer blogId, Integer userId);
	
	// 未完成
	int cachBlog(NewBlog blog, Integer userId);
	
	// 未完成
	int publishBlog(NewBlog blog, Integer userId);
	
	/**
	 * 新建评论
	 * @param comment 评论信息
	 * @param userId 用户id
	 * @return 整数类型，200 成功，-1 非本人操作，-2 系统异常
	 */
	int createComment(Comment comment, Integer userId);
	
	/**
	 * 删除自己的评论
	 * @param commentId 评论id
	 * @param userId 用户id
	 * @return 整数类型，200 成功，-1 非本人操作，-2 删除失败
	 */
	int deleteComment(Integer commentId, Integer userId);
	
	/**
	 * 用户所做过的评论的数目
	 * @param userId
	 * @return Int类型，评论的数目
	 */
	int numberOfCommentYouMade(Integer userId);
	
	/**
	 * 分页返回用户所做过的所有评论
	 * @param userId 用户id
	 * @param page 页码
	 * @return list，评论列表
	 */
	List<Comment> allCommentYouMade(Integer userId, Integer page);
	
	// 未完成
	List<Comment> allCommentYouGet(Integer userId, Integer page);
	
	/**
	 * 根据用户id分页返回博客列表
	 * @param userId 用户id
	 * @param page 页码
	 * @return List，简单博客信息列表
	 */
	List<BlogList> blog(Integer userId, int page);
	
	/**
	 * 根据用户id分页返回博客缓存列表
	 * @param userId 用户id
	 * @param page 页码
	 * @return List，简单博客信息列表
	 */
	List<BlogList> cachBlog(Integer userId, int page);
	
	/**
	 * 根据用户id分页返回垃圾箱中博客列表
	 * @param userId 用户id
	 * @param page 页码
	 * @return List，简单博客信息列表
	 */
	List<BlogList> trashBinBlog(Integer userId, int page);
	
	/**
	 * 获取用户信息
	 * @param userId 用户id
	 * @return 用户类，返回用户的具体信息
	 */
	User getUserData(Integer userId);
}
