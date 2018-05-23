package com.blog.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.blog.util.request.BlogWithTag;
import com.blog.util.request.NewBlog;
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
	public boolean deleteTag(Integer tagId) {
		Tag tag = tagDao.findTagById(tagId);
		if (tag == null) {
			return false;
		} else {
			return tagDao.deleteTag(tag);
		}
	}

	@Override
	public boolean setTag(BlogWithTag data) {
		Integer blogId = data.getBlogId();
		List<Integer> tagIds = data.getTagId();
		for (Integer tagId : tagIds) {
			if (!tagDao.setTag(tagId, blogId)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public List<BlogList> selectTag(Integer tagId) {
		List<BlogList> blogLists = new ArrayList<>();
		Set<Blog> blog = tagDao.findTagById(tagId).getBlogs();
		if (blog != null) {/*
			for(Blog Blog:blog) {
				blogLists.add(new BlogList(Blog.getBlogId(),Blog.getBlogTitle(),Blog.getNumberOfAgree(),
						Blog.getBlogState(),Blog.getPostTime(),Blog.getUserId()));
			}*/
		}
		return blogLists;
	}

	@Override
	public List<Tag> selectTagById(Integer userId) {
		return tagDao.findTagByUserId(userId);
	}
	
	@Override
    public List<Tag> selectTagByBlog(Integer blogId) {
		Blog blog = blogDao.findBlogById(blogId);
		if (blog == null) {
			// 不存在
			return null;
		}
		Set<Tag> tags = blog.getTags();
		System.out.println(tags);
		
		List<Tag> tag = new ArrayList<>();
		for (Tag t : tags) {
			Tag temp = new Tag(t.getTagId(), t.getUserId(), t.getTagContent(), null);
			tag.add(temp);
		}
		return tag;
	}
	
	
	@Override
	public boolean createBlog(NewBlog newBlog, Integer userId) {
		Timestamp tx = new Timestamp(new Date().getTime());
		List<Integer> temp = newBlog.getTags();
		Set<Tag> tags = new HashSet<>();
		if (temp != null) {
			for (Integer integer : temp) {
				tags.add(tagDao.findTagById(integer));
			}
		}
		Blog blog = new Blog(null, newBlog.getBlogTitle(), newBlog.getBlogContent(), 
				0, 0, 0, tx, tx, userId, tags);
		return blogDao.createBlog(blog);
	}

	@Override
	public int deleteBlog(Integer blogId, Integer userId) {
		Blog blog = blogDao.findBlogById(blogId);
		if (blog.getBlogState() == 2 || blog.getUserId() != userId) {
			return -1;
		} else {
			if (blogDao.deleteBlog(blogId)) {
				return 200;
			} else {
				return -2;
			}
		}
	}
	
	@Override
	public int deleteBlogToTrashBin(Integer blogId, Integer userId) {
		Blog blog = blogDao.findBlogById(blogId);
		if (blog.getBlogState() != 0 || blog.getUserId() != userId) {
			return -1;
		} else {
			if (blogDao.deleteBlogToTrashBin(blogId)) {
				return 200;
			} else {
				return -2;
			}
		}
	}

	@Override
	public int updateBlog(NewBlog blog, Integer userId) {
		Blog blog2 = blogDao.findBlogById(blog.getBlogId());
		if (blog2.getUserId() != userId) {
			// 非操作自己的博客
			return -1;
		} else {
			blog2.setBlogTitle(blog.getBlogTitle());
			Timestamp tx = new Timestamp(new Date().getTime());
			blog2.setLastModifiedTime(tx);
			blog2.setBlogContent(blog.getBlogContent());
			if (blogDao.updateBlog(blog2)) {
				return 200;
			} else {
				// 系统异常
				return -2;
			}
		}
	}

	@Override
	public int undoDeleteBlog(Integer blogId, Integer userId) {
		Blog blog = blogDao.findBlogById(blogId);
		if (blog.getBlogState() != 1 || blog.getUserId() != userId) {
			return -1;
		} else {
			if (blogDao.undoDeleteBlog(blogId)) {
				return 200;
			} else {
				return -2;
			}
		}
	}

	@Override
	public int cachBlog(NewBlog blog, Integer userId) {
		return 200;
		//return blogDao.cachBlog(blog);
	}
	
	@Override
	public int publishBlog(NewBlog blog, Integer userId) {
		return 200;
	}

	@Override
	public int updateUserData(User user, Integer userId) {
		if (user.getUserId() != userId) {
			// -1 代表修改了非本人的信息
			return -1;
		} else {
			User newUser = userDao.findUserById(userId);
			newUser.setNickName(user.getNickName());
			newUser.setPhoneNumber(user.getPhoneNumber());
			newUser.seteMail(user.geteMail());
			newUser.setProfile(user.getProfile());
			if (userDao.updateUserData(newUser)) {
				return 200;
			} else {
				// 表示更新错误
				return -2;
			}
		}
	}

	@Override
	public int createComment(Comment comment, Integer userId) {
		if (userId != comment.getUserId()) {
			// 代表非本人评论
			return -1;
		} else {
			comment.setStatus(0);
			Timestamp timestamp = new Timestamp(new Date().getTime());
			comment.setSendTime(timestamp);
			if (commentDao.createComment(comment)) {
				return 200;
			} else {
				// 表示创建评论失败
				return -2;
			}
		}
	}

	@Override
	public int deleteComment(Integer commentId, Integer userId) {
		Comment comment = commentDao.findCommentById(commentId);
		if (comment == null) {
			// 删除失败
			return -2;
		}
		if (userId != comment.getUserId() || comment.getStatus() != 0) {
			// 并非对本人评论进行操作
			return -1;
		} else {
			if (commentDao.deleteComment(commentId)) {
				return 200;
			} else {
				// 删除失败
				return -2;
			}
		}
	}

	@Override
	public List<Comment> allCommentYouMade(Integer userId, Integer page) {
		return commentDao.allCommentYouMade(userId, page);
	}

	@Override
	public List<Comment> allCommentYouGet(Integer userId, Integer page) {
		return commentDao.allCommentYouGet(userId, page);
	}

	@Override
	public List<BlogList> blog(Integer userId, int page) {
		List<Blog> blogs = blogDao.listPageBlog(userId, page);
		List<BlogList> temp = new ArrayList<>();
		for (Blog blog : blogs) {
			temp.add(new BlogList(blog.getBlogId(), blog.getBlogTitle(), blog.getNumberOfAgree(), blog.getBlogState(), blog.getPostTime(), blog.getUserId()));
		}
		return temp;
	}

	@Override
	public List<BlogList> cachBlog(Integer userId, int page) {
		List<Blog> blogs = blogDao.listPageCachBlog(userId, page);
		List<BlogList> temp = new ArrayList<>();
		for (Blog blog : blogs) {
			temp.add(new BlogList(blog.getBlogId(), blog.getBlogTitle(), blog.getNumberOfAgree(), blog.getBlogState(), blog.getPostTime(), blog.getUserId()));
		}
		return temp;
	}

	@Override
	public List<BlogList> trashBinBlog(Integer userId, int page) {
		List<Blog> blogs = blogDao.listPageTrashBinBlog(userId, page);
		List<BlogList> temp = new ArrayList<>();
		for (Blog blog : blogs) {
			temp.add(new BlogList(blog.getBlogId(), blog.getBlogTitle(), blog.getNumberOfAgree(), blog.getBlogState(), blog.getPostTime(), blog.getUserId()));
		}
		return temp;
	}
}