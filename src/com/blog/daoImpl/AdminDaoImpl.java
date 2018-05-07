package com.blog.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.AdminDao;
import com.blog.domain.Admin;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("adminDaoImpl")
public class AdminDaoImpl extends HibernateUtil implements AdminDao {

	@Autowired
	@Qualifier
	private SessionFactory sessionFactory;
	
	@Override
	public Admin findAdminById(String adminId) {
		Admin admin = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			admin = (Admin) session.get(Admin.class, adminId);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return admin;
	}

}
