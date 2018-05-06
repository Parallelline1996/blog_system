package com.blog.daoImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.blog.dao.AdminDao;
import com.blog.domain.Admin;
import com.blog.util.HibernateUtil;

@Repository
@Qualifier("adminDaoImpl")
public class AdminDaoImpl extends HibernateUtil implements AdminDao {

	@Override
	public Admin findAdminById(String adminId) {
		return null;
	}

}
