package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.CommentDao;
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
		// 将评论状态置为1
		comment.setStatus(1);
		return save(comment);
	}

	@Override
    public int numberOfCommentYouMade(Integer userId) {
		int number = 0;
		Session session = sessionFactory.openSession();
		String hql = "from Comment where userId = " + userId +" and status = 0";
		try {
			Query query = session.createQuery(hql);
			// 转为List，然后统计数目size
			number = (int)query.list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return number;
	}
	
	@Override
	public List<Comment> allCommentYouMade(Integer userId, Integer page) {
		String hql = "from Comment where userId = " + userId +" and status = 0";
		List<Object> temp = listpage(hql, page, 5);
		List<Comment> comments = new ArrayList<>();
		for (Object o : temp) {
			comments.add((Comment)o);
		}
		return comments;
	}

	@Override
	public List<Comment> allCommentYouGet(Integer userId, Integer page) {
		String hql = "from Comment where ";
		List<Object> temp = listpage(hql, page, 5);
		List<Comment> comments = new ArrayList<>();
		for (Object o : temp) {
			comments.add((Comment)o);
		}
		return comments;
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

	@Override
	public List<Comment> findCommentByBlog(Integer blogId, Integer page) {
		// objectOption 评论对象的选择，1表示博客
		String hql = "from Comment where objectOption = 1 and commentObjectId = " + blogId +" and status = 0";
		List<Object> temp = listpage(hql, page, 5);
		List<Comment> comments = new ArrayList<>();
		for (Object o : temp) {
			comments.add((Comment)o);
		}
		return comments;
	}

	@Override
	public int numberOfCommentsByBlog(Integer blogId) {
		String hql = "from Comment where ojbectOption = 1 and commentObjectId = " + blogId + " and status = 0";
		Session session = sessionFactory.openSession();
		int number = -1;
		try {
			number = (int)session.createQuery(hql).list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return number;
	}

	
}