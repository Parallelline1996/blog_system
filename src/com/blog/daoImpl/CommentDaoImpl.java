package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.CommentDao;
import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("commentDaoImpl")
public class CommentDaoImpl extends HibernateUtil implements CommentDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createComment(Comment comment) {
		return save(comment);
	}

	@Override
	public boolean deleteComment(Integer commentId) {
		Comment comment = findCommentById(commentId);
		comment.setStatus(1);
		return save(comment);
	}

	@Override
	public List<Comment> allCommentYouMade(Integer userId) {
		// TODO Auto-generated method stub
		String hql = "from Comment where userId = ? and status = 0";
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Comment> res = null;
		Query query = null;
		try {
			query = session.createQuery(hql).setParameter(0, userId);
			res = query.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public List<Comment> allCommentYouGet(Integer userId) {
		// TODO Auto-generated method stub
		String hql2 = "from Blog where userId = ?";
		Session session2 = getSessionFactory().openSession();
		List<Blog> b = new ArrayList<>();
		List<Comment> res = null;
		Query q = null;
		try {
			q = session2.createQuery(hql2).setParameter(0, userId);
			b = q.list();
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			session2.close();
		}
		if(b!=null)
		{
			for(Blog Blog:b) {
				String hql = "from Comment where commentObjectId = ? and status = 0";
				
				Session session = getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();

				Query query = null;
				try {
					query = session.createQuery(hql).setParameter(0, Blog);
					res = query.list();
				} catch (Exception e) {
		            e.printStackTrace();
				}finally{
					session.close();
				}
			}
		}
		return res;
	}

	@Override
	public Comment findCommentById(Integer commentId) {
		Session session = sessionFactory.openSession();
		Comment comment = null;
		try {
			comment = (Comment)session.get(Comment.class, commentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return comment;
	}

	
}