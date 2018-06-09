package com.blog.service;

import com.blog.domain.User;
import com.blog.util.request.LoginData;

public interface AccountService {
	
	/**
	 * 新建用户
	 * @param data 用于注册的信息
	 * @return 整数类型，-1 代表邮箱已被注册，-2 系统异常，200 代表成功
	 */
	int createUser(User data);
	
	/**
	 * 登陆函数
	 * @param data 用于登陆的信息
	 * @return -1 代表账号不存在，-2 代表密码错误，正数代表用户id
	 */
	int login(LoginData data);
}
