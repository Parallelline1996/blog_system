package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.util.HibernateUtil;
import com.blog.util.response.BlogList;


@Repository
@Qualifier("blogDaoImpl")
public class BlogDaoImpl extends HibernateUtil implements BlogDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createBlog(Blog blog) {
		return save(blog);
	}

	@Override
	public boolean deleteBlog(Integer blogId) {
		Blog blog = findBlogById(blogId);
		blog.setBlogState(2);
		return save(blog);
	}

	@Override
	public boolean deleteBlogToTrashBin(Integer blogId) {
		Blog blog = findBlogById(blogId);
		// blogState: 0->正常  1->垃圾箱  2->彻底删除 
		blog.setBlogState(1);
		return save(blog);
	}
	
	@Override
	public boolean updateBlog(Blog blog) {
		return update(blog);
	}

	@Override
	public Blog findBlogById(Integer blogId) {
		Blog blog = null;
		Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		try {
			blog = (Blog) session.get(Blog.class, blogId);
			//transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//transaction.rollback();
		} finally {
			session.close();
		}/*
		String hql = "select tag.tagId from blog_tag where blogId = ?";
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(hql).setParameter(0, blogId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		Set<Integer> temp = new HashSet<>();
		if (query != null) {
			List<Object> o = query.list();
			for (Object object : o) {
				temp.add((Integer)object);
			}
		}*/
		
		return blog;
	}

	@Override
	public boolean undoDeleteBlog(Integer blogId) {
		Blog blog = findBlogById(blogId);
		blog.setBlogState(0);
		return save(blog);
	}

	@Override
	public boolean cachBlog(Blog blog) {
		// -1表示该博客未完成
		blog.setBlogState(-1);
		return save(blog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> allBlog() {
		String hql = "from Blog where b_status = 0";
		
		Session session = getSessionFactory().openSession();
		List<Blog> res = null;
		Query query = null;
		try {
			query = session.createQuery(hql);
			res = query.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		
		return res;
	}

	@Override
	public List<Blog> allBlogByPage(int page){
		String hql = "from Blog where b_status = 0";
		List<Blog> blogs = new ArrayList<>();
		List<Object> temp = listpage(hql, page, 5);
		for (Object object : temp) {
			blogs.add((Blog)object);
		}
		return blogs;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> allBlogById(Integer userId) {
		// 未被检验
		String hql = "from Blog as b where b.id  = 'userId'";
		return (List<Blog>) findByHql(hql, null);
	}

	@Override
	public List<Blog> listPageAllBlog(int pageNo, int pageNum) {
		String hql = "from Blog";
		List<Object> temp = null;
		temp = listpage(hql, pageNo, pageNum);
		List<Blog> blogs = new ArrayList<>();
		for (Object object : temp) {
			blogs.add((Blog)object);
		}
		return blogs;
	}

	@Override
	public List<Blog> listPageAllBlogById(int pageNo, int pageNum) {
		// TODO Auto-generated method stub
		// 暂时不完成
		return null;
	}

	@Override
	public List<BlogList> selectTag(Blog blog, Integer tagId) {
		// 暂时跳过
		return null;
	}

}