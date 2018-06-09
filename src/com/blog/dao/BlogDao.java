package com.blog.dao;

import java.util.List;

import com.blog.domain.Blog;
import com.blog.util.response.BlogList;

public interface BlogDao {

	/**
	 * 新建博客
	 * @param blog 博客类，传入博客的信息
	 * @return boolean 成功新建则返回为true
	 */
	boolean createBlog(Blog blog);
	
	/**
	 * 彻底删除博客
	 * @param blogId 博客Id
	 * @return boolean
	 */
	boolean deleteBlog(Integer blogId);
	
	/**
	 * 将博客放入垃圾箱
	 * @param blogId 博客Id
	 * @return boolean
	 */
	boolean deleteBlogToTrashBin(Integer blogId);
	
	/**
	 * 更新博客
	 * @param blog 博客信息
	 * @return boolean
	 */
	boolean updateBlog(Blog blog);
	
	/**
	 * 通过id查找博客
	 * @param blogId 博客id
	 * @return Blog类型
	 */
	Blog findBlogById(Integer blogId);
	
	/**
	 * 撤销删除博客，即从垃圾箱中恢复博客
	 * @param blogId 博客Id
	 * @return boolean
	 */
	boolean undoDeleteBlog(Integer blogId);
	
	/**
	 * 缓存博客
	 * @param blog 博客信息
	 * @return boolean
	 */
	boolean cachBlog(Blog blog);
	
	/**
	 * 返回所有未删除的博客
	 * @return 博客的list
	 */
	List<Blog> allBlog();
	
	/**
	 * 分页返回所有未删除的博客，这里默认每页有5个博客
	 * @param page 页码
	 * @return 博客的list
	 */
	List<Blog> allBlogByPage(int page);
	
	/**
	 * 某个用户所写的所有未删除博客
	 * @param userId 用户的Id
	 * @return 博客的List
	 */
	List<Blog> allBlogById(Integer userId);
	
	/**
	 * 展示所有的博客
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	List<Blog> listPageAllBlog(int pageNo, int pageNum);
	
	// 未完成
	List<BlogList> selectTag(Blog blog, Integer tagId);
	
	/**
	 * 分页展示用户的所有blog
	 * @param userId 用户Id
	 * @param pageNo 页码
	 * @return 博客List
	 */
	List<Blog> listPageBlog(int userId, int pageNo);
	
	/**
	 * 分页展示缓存中的博客
	 * @param userId 用户id
	 * @param pageNo 页码
	 * @return 博客list
	 */
	List<Blog> listPageCachBlog(int userId, int pageNo);
	
	/**
	 * 分页展示回收站里的博客
	 * @param userId 用户id
	 * @param pageNo 页码
	 * @return 博客List
	 */
	List<Blog> listPageTrashBinBlog(int userId, int pageNo);
}
