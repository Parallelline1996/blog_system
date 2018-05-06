package com.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.service.UserService;
import com.blog.util.request.BlogWithTag;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Override
	public int createFollow(String ownId, String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFollow(String ownId, String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserSimpleData> visitFollows(String ownId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserSimpleData> visitFans(String ownId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfFollows(String ownId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numberOfFans(String ownId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean agree(String userId, String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disagree(String userId, String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createTag(Tag tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTag(Tag tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTag(String tagId, String blogId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<BlogList> selectTag(String tagId) {
		return null;
	}

	@Override
	public boolean createBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBlog(String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean undoDeleteBlog(String blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cachBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserData(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createComment(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteComment(String commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comment> allCommentYouMade(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> allCommentYouGet(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
