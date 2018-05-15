package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.dao.FollowDao;
import com.blog.dao.TagDao;
import com.blog.dao.UpDownDao;
import com.blog.dao.UserDao;
import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.service.UserService;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;
import java.util.Set;
@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("followDaoImpl")
	private FollowDao followDao;
	
	@Autowired
	@Qualifier("tagDaoImpl")
	private TagDao tagDao;
	
	@Autowired
	@Qualifier("blogDaoImpl")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("upDownDaoImpl")
	private UpDownDao upDownDao;
	
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("commentDaoImpl")
	private CommentDao commentDao;
	
	@Override
	public int createFollow(Integer ownId, Integer userId) {
		if (followDao.existFollow(ownId, userId)) {
			// -1 代表已经关注
			return -1;
		} else if (followDao.createFollow(ownId, userId)) {
			return 200;
		} else {
			// -2 代表系统异常
			return -2;
		}

	}

	@Override
	public int deleteFollow(Integer ownId, Integer userId) {
		if (!followDao.existFollow(ownId, userId)) {
			// 表示并未关注
			return -1;
		} else if (followDao.deleteFollow(ownId, userId)) {
			return 200;
		} else {
			// 表示系统异常
			return -2;
		}
	}

	@Override
	public List<UserSimpleData> visitFollows(Integer ownId) {
		List<Integer> s = followDao.visitFollow(ownId);
		List<UserSimpleData> UserData = new ArrayList<>();
		if (s != null) {
			for (Integer integer : s) {
				User user = userDao.findUserById(integer);
				UserData.add(new UserSimpleData(integer, user.getNickName(), user.getProfile(), user.getNumOfFans(), user.getNumOfAttention()));
			}
		}
		return UserData;

	}

	@Override
	public List<UserSimpleData> visitFans(Integer ownId) {
		List<Integer> s = followDao.visitFans(ownId);
		List<UserSimpleData> UserData = new ArrayList<>();
		if (s != null) {
			for (Integer integer : s) {
				User user = userDao.findUserById(integer);
				UserData.add(new UserSimpleData(integer, user.getNickName(), user.getProfile(), user.getNumOfFans(), user.getNumOfAttention()));
			}
		}
		return UserData;
	}

	@Override
	public int numberOfFollows(Integer ownId) {
		return userDao.findUserById(ownId).getNumOfAttention();
	}

	@Override
	public int numberOfFans(Integer ownId) {
		return userDao.findUserById(ownId).getNumOfFans();
	}

	@Override
	public boolean agree(Integer userId, Integer blogId) {
		return upDownDao.agree(userId, blogId);
	}

	@Override
	public boolean disagree(Integer userId, Integer blogId) {
		return upDownDao.disagree(userId, blogId);
	}

	@Override
	public boolean createTag(Tag tag, Integer userId) {
		tag.setUserId(userId);
		return tagDao.createTag(tag);
	}

	@Override
	public boolean deleteTag(Tag tag) {
		return tagDao.deleteTag(tag);
	}

	@Override
	public boolean setTag(Integer tagId, Integer blogId) {
		return tagDao.setTag(tagId, blogId);
	}
	
	@Override
	public List<BlogList> selectTag(Integer tagId) {
		List<BlogList> blogLists=new ArrayList<>();
		Set<Blog> blog = tagDao.findTagById(tagId).getBlogs();
		if(blog!=null) {
			for(Blog Blog:blog) {/*
				blogLists.add(new BlogList(Blog.getBlogId(),Blog.getBlogTitle(),Blog.getNumberOfAgree(),
						Blog.getBlogState(),Blog.getPostTime(),Blog.getUserId()));*/
			}
		}
		return blogLists;
	}

	@Override
	public boolean createBlog(Blog blog) {
		return blogDao.createBlog(blog);
	}

	@Override
	public boolean deleteBlog(Integer blogId) {
		return blogDao.deleteBlog(blogId);
	}

	@Override
	public boolean updateBlog(Blog blog) {
		return blogDao.updateBlog(blog);
	}

	@Override
	public boolean undoDeleteBlog(Integer blogId) {
		return blogDao.undoDeleteBlog(blogId);
	}

	@Override
	public boolean cachBlog(Blog blog) {
		return blogDao.cachBlog(blog);
	}

	@Override
	public boolean updateUserData(User user) {
		return userDao.updateUserData(user);
	}

	@Override
	public boolean createComment(Comment comment) {
		return commentDao.createComment(comment);
	}

	@Override
	public boolean deleteComment(Integer commentId) {
		return commentDao.deleteComment(commentId);
	}

	@Override
	public List<Comment> allCommentYouMade(Integer userId) {
		return commentDao.allCommentYouMade(userId);
	}

	@Override
	public List<Comment> allCommentYouGet(Integer userId) {
		return commentDao.allCommentYouGet(userId);
	}
}