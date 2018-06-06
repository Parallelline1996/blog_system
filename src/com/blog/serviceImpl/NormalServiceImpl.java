package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.dao.UpDownDao;
import com.blog.domain.Blog;
import com.blog.domain.Tag;
import com.blog.service.NormalService;
import com.blog.util.response.BlogData;
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
		List<Blog> blogs = blogDao.allBlog();
		List<BlogList> bloglist =  new ArrayList<>();
		// 将 Blog 列表转为 BlogList
		try {
			for(Blog blog : blogs) {
				bloglist.add(new BlogList(blog.getBlogId(), blog.getBlogTitle(), 
						blog.getNumberOfAgree(), blog.getBlogState(), blog.getPostTime(), blog.getUserId()));
			}
		}catch (NullPointerException e) {
			bloglist = null;
		}
		return bloglist; 
	}

	
	// 对上面的功能进行分页处理
	@Override
	public List<BlogList> readBlogByPage(int page) {
		List<Blog> blogs = blogDao.allBlogByPage(page);
		List<BlogList> bloglist =  new ArrayList<>();
		try {
			for(Blog blog : blogs) {
				bloglist.add(new BlogList(blog.getBlogId(), blog.getBlogTitle(), 
						blog.getNumberOfAgree(), blog.getBlogState(), blog.getPostTime(), blog.getUserId()));
			}
		}catch (NullPointerException e) {
			bloglist = null;
		}
		return bloglist; 
	}

	// 这里是对于未注册用户使用的
	@Override
	public BlogData findBlogById(Integer blogId) {
		Blog blog = blogDao.findBlogById(blogId);
		Set<Tag> set = blog.getTags();
		List<String> seTags = new ArrayList<>();
		// 将 set 中的标签内容进行转换
		for (Tag tag : set) {
			System.out.println(tag.getTagContent());
			seTags.add(tag.getTagContent());
		}
		// 0代表正常，1代表进入垃圾箱，2代表彻底删除，-1代表缓存
		if (blog.getBlogState() != 0) {
			return null;
		}
		BlogData data = new BlogData(blog.getBlogId(), blog.getBlogTitle(), blog.getBlogContent(), blog.getNumberOfAgree(), blog.getNumberOfDisagree(),
				blog.getBlogState(), blog.getPostTime(), blog.getLastModifiedTime(), blog.getUserId(), seTags);
		return data;
	}



}
