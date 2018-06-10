package com.blog.dao;

public interface AdminDao {

	/**
	 * 判断邮箱账号是否存在
	 * @param email 邮箱账号
	 * @return boolean 存在即true
	 */
	boolean adminExist(String email);
	
	/**
	 * 检查邮箱账号与密码是否相符
	 * @param email 邮箱账号
	 * @param password 密码
	 * @return 整数类型 -1代表邮箱账号不存在，大于零的数值表示管理员的id
	 */
	int checkPassword(String email,String password);
}
