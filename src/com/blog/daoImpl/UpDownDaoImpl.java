package com.blog.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.UpDownDao;
import com.blog.dao.UserDao;
import com.blog.dao.BlogDao;
import com.blog.domain.Follow;
import com.blog.domain.FollowUpId;
import com.blog.domain.User;
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
	
	//这三个函数搞不懂是怎么回事
	@Override
	public int upOrDown(String userId, String blogId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from UpOrDownId where userId = ? and blogId = ? ";
		User user = null;
		try {
			// 当确定返回值为1个或null时，使用uniqueResult
			user = (User)session.createQuery(hql)
					.setParameter(0, userId).setParameter(1, blogId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 是否存在，存在的话返回的是true
		if (user != null)
			return 1;
		return 0;
	}

	@Override
	public boolean agree(String userId, String blogId) {
		UpOrDownId upOrDownId = new UpOrDownId(userId, blogId);
		UpOrDown upOrDown = new UpOrDown(upOrDownId,0);
		Blog blogger = blogDao.findBlogById(blogId);
		if(blogger!=null) {
			blogger.setNumberOfAgree(blogger.getNumberOfAgree() + 1);
		}
		return save(upOrDown);
	}

	@Override
	public boolean disagree(String userId, String blogId) {
		// TODO Auto-generated method stub
		UpOrDownId upOrDownId = new UpOrDownId(userId, blogId);
		UpOrDown upOrDown = new UpOrDown(upOrDownId,1);
		Blog blogger = blogDao.findBlogById(blogId);
		if(blogger!=null) {
			blogger.setNumberOfAgree(blogger.getNumberOfDisagree() + 1);
		}
		return save(upOrDown);

	}

}
