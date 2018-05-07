package com.blog.daoImpl;

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
	public boolean deleteComment(String commentId) {
		Comment comment = findCommentById(commentId);
		comment.setStatus(1);
		return true;
	}

	@Override
	public List<Comment> allCommentYouMade(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> allCommentYouGet(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findCommentById(String commentId) {
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
