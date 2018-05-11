package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
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
	public int createFollow(String ownId, String userId) {
		// TODO Auto-generated method stub
		if(followDao.createFollow(ownId, userId))
		{
			return 200;
		}
		else {
			return -1;
		}

	}

	@Override
	public int deleteFollow(String ownId, String userId) {
		// TODO Auto-generated method stub
		if(followDao.deleteFollow(ownId, userId))
		{
			return 200;
		}
		else {
			return -1;
		}
	}

	@Override
	public List<UserSimpleData> visitFollows(String ownId) {
		// TODO Auto-generated method stub
		List<String> s = followDao.visitFollow(ownId);
		List<UserSimpleData> u_s = new ArrayList<>();
		if(s!=null)
		{
			for(String String: s) {
				User u = userDao.findUserById(String);
				u_s.add(new UserSimpleData(u.getUserId(),u.getNickName(),u.getProfile(),
						u.getNumOfFans(),u.getNumOfAttention()));
			}
		}
		return u_s;

	}

	@Override
	public List<UserSimpleData> visitFans(String ownId) {
		// TODO Auto-generated method stub
		List<String> s = followDao.visitFans(ownId);
		List<UserSimpleData> u_s = new ArrayList<>();
		if(s!=null)
		{
			for(String String: s) {
				User u = userDao.findUserById(String);
				u_s.add(new UserSimpleData(u.getUserId(),u.getNickName(),u.getProfile(),
						u.getNumOfFans(),u.getNumOfAttention()));
			}
		}
		return u_s;
	}

	@Override
	public int numberOfFollows(String ownId) {
		// TODO Auto-generated method stub
		return userDao.findUserById(ownId).getNumOfAttention();
	}

	@Override
	public int numberOfFans(String ownId) {
		// TODO Auto-generated method stub
		return userDao.findUserById(ownId).getNumOfFans();
	}

	@Override
	public boolean agree(String userId, String blogId) {
		// TODO Auto-generated method stub
		return upDownDao.agree(userId, blogId);
	}

	@Override
	public boolean disagree(String userId, String blogId) {
		// TODO Auto-generated method stub
		return upDownDao.disagree(userId, blogId);
	}

	@Override
	public boolean createTag(Tag tag) {
		// TODO Auto-generated method stub
		return tagDao.createTag(tag);
	}

	@Override
	public boolean deleteTag(Tag tag) {
		// TODO Auto-generated method stub
		return tagDao.deleteTag(tag);
	}

	@Override
	public boolean setTag(String tagId, String blogId) {
		// TODO Auto-generated method stub
		return tagDao.setTag(tagId, blogId);
	}
	
	@Override
	public List<BlogList> selectTag(String tagId) {
		List<BlogList> blogLists=new ArrayList<>();
		Set<Blog> blog = tagDao.findTagById(tagId).getBlogs();
		if(blog!=null) {
			for(Blog Blog:blog) {
				blogLists.add(new BlogList(Blog.getBlogId(),Blog.getBlogTitle(),Blog.getNumberOfAgree(),
						Blog.getBlogState(),Blog.getPostTime(),Blog.getUserId()));
			}
		}
		return blogLists;
	}

	@Override
	public boolean createBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.createBlog(blog);
	}

	@Override
	public boolean deleteBlog(String blogId) {
		// TODO Auto-generated method stub
		return blogDao.deleteBlog(blogId);
	}

	@Override
	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.updateBlog(blog);
	}

	@Override
	public boolean undoDeleteBlog(String blogId) {
		// TODO Auto-generated method stub
		return blogDao.undoDeleteBlog(blogId);
	}

	@Override
	public boolean cachBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.cachBlog(blog);
	}

	@Override
	public boolean updateUserData(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserData(user);
	}

	@Override
	public boolean createComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.createComment(comment);
	}

	@Override
	public boolean deleteComment(String commentId) {
		// TODO Auto-generated method stub
		return commentDao.deleteComment(commentId);
	}

	@Override
	public List<Comment> allCommentYouMade(String userId) {
		// TODO Auto-generated method stub
		return commentDao.allCommentYouMade(userId);
	}

	@Override
	public List<Comment> allCommentYouGet(String userId) {
		// TODO Auto-generated method stub
		return commentDao.allCommentYouGet(userId);
	}
}