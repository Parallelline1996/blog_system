package com.blog.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.UpDownDao;
import com.blog.dao.UserDao;
import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.util.HibernateUtil;
import com.blog.domain.UpOrDownId;
import com.blog.domain.UpOrDown;

@Repository
@Qualifier("upDownDaoImpl")
public class UpDownDaoImpl extends HibernateUtil implements UpDownDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("blogDaoImpl")
	private BlogDao blogDao;
	
	// 判断是否在UpORDown表中插入了对应的 userId -> blogId
	@Override
	public int upOrDown(Integer userId, Integer blogId) {
		Session session = sessionFactory.openSession();
		String hql = "from UpOrDown where UpOrDownId.userId = ? and UpOrDownId.blogId = ? ";
		UpOrDown upOrDown = null;
		try {
			// 当确定返回值为1个或null时，使用uniqueResult
			upOrDown = (UpOrDown)session.createQuery(hql)
					.setParameter(0, userId).setParameter(1, blogId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 是否存在，存在的话返回的是true
		if (upOrDown == null) {
			// 表示用户没有进行点赞或点踩
			return 0;
		} else {
			// 返回 upOrDown 对应的 state，如果为1表示赞，-1表示踩
			return upOrDown.getState();
		}
	}

	@Override
	public boolean agree(Integer userId, Integer blogId) {
		if (upOrDown(userId, blogId) == 1) {
			return true;
		} else if (upOrDown(userId, blogId) == -1) {
			// 回头看看这里用不用用transaction写
			UpOrDownId upOrDownId = new UpOrDownId(userId, blogId);
			UpOrDown upOrDown = new UpOrDown(upOrDownId, 1);
			Blog blog = blogDao.findBlogById(blogId);
			blog.setNumberOfAgree(blog.getNumberOfAgree() + 1);
			blog.setNumberOfDisagree(blog.getNumberOfDisagree() - 1);
			return update(upOrDown) && update(blog);
		} else {
			UpOrDownId upOrDownId = new UpOrDownId(userId, blogId);
			UpOrDown upOrDown = new UpOrDown(upOrDownId,1);
			Blog blog = blogDao.findBlogById(blogId);
			if(blog != null) {
				blog.setNumberOfAgree(blog.getNumberOfAgree() + 1);
				update(blog);
			}
			return save(upOrDown);
		}
	}

	@Override
	public boolean disagree(Integer userId, Integer blogId) {
		if (upOrDown(userId, blogId) == -1) {
			return true;
		} else if (upOrDown(userId, blogId) == 1) {
			// 回头看看这里用不用用transaction写
			UpOrDownId upOrDownId = new UpOrDownId(userId, blogId);
			UpOrDown upOrDown = new UpOrDown(upOrDownId, -1);
			Blog blog = blogDao.findBlogById(blogId);
			blog.setNumberOfAgree(blog.getNumberOfAgree() - 1);
			blog.setNumberOfDisagree(blog.getNumberOfDisagree() + 1);
			return update(upOrDown) && update(blog);
		} else {
			UpOrDownId upOrDownId = new UpOrDownId(userId, blogId);
			UpOrDown upOrDown = new UpOrDown(upOrDownId, -1);
			Blog blog = blogDao.findBlogById(blogId);
			if(blog != null) {
				blog.setNumberOfAgree(blog.getNumberOfDisagree() + 1);
				update(blog);
			}
			return save(upOrDown);
		}

	}

}
