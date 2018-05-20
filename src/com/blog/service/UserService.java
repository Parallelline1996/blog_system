package com.blog.service;

import java.util.List;

import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.util.request.BlogWithTag;
import com.blog.util.request.NewBlog;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

public interface UserService {
	
	int createFollow(Integer ownId, Integer userId);
	
	int deleteFollow(Integer ownId, Integer userId);
	
	List<UserSimpleData> visitFollows(Integer ownId);
	
	List<UserSimpleData> visitFans(Integer ownId);
	
	int numberOfFollows(Integer ownId);
	
	int numberOfFans(Integer ownId);
	
	boolean createTag(Tag tag, Integer userId);
	
	boolean deleteTag(Integer tagId);
	
	boolean setTag(BlogWithTag data);
	
	List<BlogList> selectTag(Integer tagId);
	
	boolean agree(Integer userId, Integer blogId);
	
	boolean disagree(Integer userId, Integer blogId);
	
	int updateUserData(User user, Integer userId);
	
	// 在这里对数据进行转换
	boolean createBlog(NewBlog blog, Integer userId);
	
	int deleteBlog(Integer blogId, Integer userId);
	
	int deleteBlogToTrashBin(Integer blogId, Integer userId);
	
	int updateBlog(NewBlog blog, Integer userId);
	
	int undoDeleteBlog(Integer blogId, Integer userId);
	
	int cachBlog(NewBlog blog, Integer userId);
	
	int createComment(Comment comment, Integer userId);
	
	int deleteComment(Integer commentId, Integer userId);
	
	List<Comment> allCommentYouMade(Integer userId, Integer page);
	
	List<Comment> allCommentYouGet(Integer userId, Integer page);
	
	List<BlogList> blog(Integer userId, int page);
	
	List<BlogList> cachBlog(Integer userId, int page);
	
	List<BlogList> trashBinBlog(Integer userId, int page);
}
