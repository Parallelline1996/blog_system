package com.blog.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.CommentDao;
import com.blog.domain.Comment;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("commentDaoImpl")
public class CommentDaoImpl extends HibernateUtil implements CommentDao {

	@Override
	public boolean createComment(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteComment(String commentId) {
		// TODO Auto-generated method stub
		return false;
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

}
