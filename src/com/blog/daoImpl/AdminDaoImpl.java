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
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Admin findAdminById(String adminId) {
		Admin admin = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			admin = (Admin)session.get(Admin.class, adminId);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return admin;
	}
	
	@Override
	public boolean adminExist(String eMail) {
		Session session = sessionFactory.openSession();
		String hql = "from Admin where eMail = ? ";
		Admin admin = null;
		try {
			// 当确定返回值为1个或null时，使用uniqueResult
			admin = (Admin)session.createQuery(hql)
					.setParameter(0, eMail)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 是否存在，存在的话返回的是true
		if (admin != null) {
			return true;			
		}
		return false;
	}
	
	@Override
	public int checkPassword(String eMail,String password) {
		Session session = sessionFactory.openSession();
		String hql = "from Admin where eMail = ? and password = ?";
		Admin admin = null;
		try {
			// 当确定返回值为1个或null时，使用uniqueResult
			admin = (Admin)session.createQuery(hql)
					.setParameter(0, eMail).setParameter(1, password)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 是否存在，存在的话返回的是true
		if (admin == null) {
			return -1;
		}
		return admin.getAdminId();
	}
}
