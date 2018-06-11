package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

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
		// 设置为2，代表彻底删除
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
		try {
			blog = (Blog) session.get(Blog.class, blogId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
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
		String hql = "from Blog where blogId  = " + userId + " and blogState = 0";
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
	public List<BlogList> selectTag(Blog blog, Integer tagId) {
		// 暂时跳过
		return null;
	}

	@Override
	public List<Blog> listPageBlog(int userId, int pageNo) {
		// 0 代表正常状态
		String hql = "from Blog where blogState = 0 and userId  = " + userId;
		List<Object> objects = listpage(hql, pageNo, 5);
		List<Blog> blogs = new ArrayList<>();
		for (Object o : objects) {
			blogs.add((Blog)o);
		}
		return blogs;
	}

	@Override
	public List<Blog> listPageCachBlog(int userId, int pageNo) {
		// -1 代表缓存态
		String hql = "from Blog where blogState = -1 and userId  = " + userId;
		List<Object> objects = listpage(hql, pageNo, 5);
		List<Blog> blogs = new ArrayList<>();
		for (Object o : objects) {
			blogs.add((Blog)o);
		}
		return blogs;
	}

	@Override
	public List<Blog> listPageTrashBinBlog(int userId, int pageNo) {
		// 1 代表进入垃圾箱
		String hql = "from Blog where blogState = 1 and userId  = " + userId;
		List<Object> objects = listpage(hql, pageNo, 5);
		List<Blog> blogs = new ArrayList<>();
		for (Object o : objects) {
			blogs.add((Blog)o);
		}
		return blogs;
	}

	@Override
	public int numberOfTrasnBinBlog(Integer userId) {
		String hql = "from Blog where userId = " + userId + " and blogState = 1";
		int number = -1;
		Session session = getSessionFactory().openSession();
		Query query = null;
		try {
			query = session.createQuery(hql);
			number = (int)query.list().size();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		
		return number;
	}
	
	@Override
	public int numberOfBlog(Integer userId) {
		String hql = "from Blog where userId = " + userId + " and blogState = 0";
		int number = -1;
		Session session = getSessionFactory().openSession();
		Query query = null;
		try {
			query = session.createQuery(hql);
			number = (int)query.list().size();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		
		return number;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> bestBlog() {
		String hql = "from Blog where blogState = 0";
		List<Object> objects = null;
		Session session = sessionFactory.openSession();
		Query query = null;
		try {
			query = session.createQuery(hql);
			objects = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (objects == null) {
			return null;
		}
		int number = objects.size();
		int[] location = new int[number];
		for (int i = 0; i < number; i++) {
			location[i] = i;
		}
		int[] temp = new int[number];
		for (int i = 0; i < number; i++) {
			Blog blog = (Blog)objects.get(i);
			temp[i] = blog.getNumberOfAgree();
		}
		for (int i = 1; i < number; i++) {
			for (int j = 0; j < number - i; j++) {
				if (temp[j] < temp[j + 1]) {
					int temp_ = temp[j + 1];
					temp[j + 1] = temp[j];
					temp[j] = temp_;
					temp_ = location[j + 1];
					location[j + 1] = location[j];
					location[j] = temp_;
				}
			}
		}
		List<Blog> blogs = new ArrayList<>();
		if (number <= 6) {
			for (int i = 0; i < number; i++) {
				Blog blog = (Blog)objects.get(location[i]);
				blogs.add(blog);
			}
		} else {
			for (int i = 0; i < 6; i++) {
				Blog blog = (Blog)objects.get(location[i]);
				blogs.add(blog);
			}
		}
		return blogs;
	}

}