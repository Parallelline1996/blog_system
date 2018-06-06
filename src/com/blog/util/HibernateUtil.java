package com.blog.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hibernate的工具类
 * @author parallel
 *
 */
public class HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//保存一个新的Object
	public boolean save(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean status = false;
		try {
			session.save(session.merge(obj));
			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}

	//更新一个新的Object
	public boolean update(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		;
		boolean status = false;
		try {
			session.update(session.merge(obj));
			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}

	//删除Object
	public boolean delete(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean status = false;
		try {
			System.out.println(obj == null);
			session.delete(session.merge(obj));
			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}

	//删除表
	public boolean deleteall(String obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean status = false;
		try {
			String hql = "delete " + obj;
			session.createQuery(hql).executeUpdate();
			tx.commit();
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}

	//执行一个HQL查询语句
	public Object findByHql(String hql, Object[] condition) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object result = null;
		try {
			Query query = session.createQuery(hql);
			if (condition.length != 0)
				for (int i = 0; i < condition.length; i++)
					query.setParameter(i, condition[i]);
			
			result = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			// tran.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	//执行一个HQL语句，并返回所有的data
	@SuppressWarnings("unchecked")
	public List<Object> findByHqlGetList(String hql, Object[] condition) {
		Query query = sessionFactory.openSession().createQuery(hql);
		for (int i = 0, len = condition.length; i < len; i++) {
			query.setParameter(i, condition[i]);
		}
		return (List<Object>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object> findByHqlGetList(String hql) {
		Query query = sessionFactory.openSession().createQuery(hql);
		return query.list();
	}
	
	// 分页显示
	@SuppressWarnings("unchecked")
	public List<Object> listpage(String hql, int pageNo, int pageNum) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(hql);

		query.setFirstResult((pageNo - 1) * pageNum);
		query.setMaxResults(pageNum);
		return (List<Object>) query.list();
	}
	
	//获取数目
	public Object getSize(String hql){
		return sessionFactory.openSession().createQuery(hql).uniqueResult();
	}
}
