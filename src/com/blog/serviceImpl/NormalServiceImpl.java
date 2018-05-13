package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.dao.UpDownDao;
import com.blog.domain.Blog;
import com.blog.service.NormalService;
import com.blog.util.response.BlogList;
@Service
@Qualifier("normalServiceImpl")
public class NormalServiceImpl implements NormalService {

	@Autowired
	@Qualifier("blogDaoImpl")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("commentDaoImpl")
	private CommentDao commentDao;
	
	@Autowired
	@Qualifier("upDownDaoImpl")
	private UpDownDao upDownDao;
	
	@Override
	public List<BlogList> readBlog() {
		// TODO Auto-generated method stub
		// 注意这里要完成转换，from blog to blogList
		List<Blog> blogs = blogDao.allBlog();
		List<BlogList> bloglist =  new ArrayList<>();
		try {
			for(Blog Blog : blogs) {
				bloglist.add(new BlogList(Blog.getBlogId(),Blog.getBlogTitle(),Blog.getNumberOfAgree(),
					Blog.getBlogState(),Blog.getPostTime(),Blog.getUserId()));
			}
		}catch (NullPointerException e) {
			bloglist = null;
			// TODO: handle exception
		}
		return bloglist; 
	}

	@Override
	public Blog findBlogById(String blogId) {
		// TODO Auto-generated method stub
		return blogDao.findBlogById(blogId);
	}


}
