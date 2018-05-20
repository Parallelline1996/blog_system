package com.blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

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
		comment.setStatus(1);
		return save(comment);
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

	
}