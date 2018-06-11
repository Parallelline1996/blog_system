package com.blog.dao;

import java.util.List;

import com.blog.domain.User;

public interface UserDao {

	/**
	 * 新建用户
	 * @param user 用户信息
	 * @return boolean
	 */
	boolean createUser(User user);
	
	/**
	 * 通过id查找用户
	 * @param userId 用户Id
	 * @return 用户的实体类
	 */
	User findUserById(Integer userId);
	
	/**
	 * 查看所有未被注销的用户
	 * @return 用户List
	 */
	List<User> allUser();
	
	/**
	 * 分页展示未被注销的用户
	 * @param page 页码
	 * @return 用户list
	 */
	List<User> allUserByPage(int page);
	
	/**
	 * 删除用户
	 * @param userId 被删除用户的id
	 * @return boolean
	 */
	boolean deleteUser(Integer userId);
	
	/**
	 * 更新用户信息
	 * @param user 用户信息
 	 * @return boolean
	 */
	boolean updateUserData(User user);
	
	/**
	 * 判断邮箱是否已经被注册
	 * @param email 邮箱
	 * @return boolean
	 */
	boolean accountExist(String email);
	
	/**
	 * 检查密码与邮箱号是否匹配
	 * @param email 邮箱号
	 * @param password 密码
	 * @return 整数类型，-1 代表不匹配，非负代表该组邮箱密码对应的用户id
	 */
	int checkPassword(String email,String password);
	
	/**
	 * 返回用户数目
	 * @return 整数类型，代表用户人数
	 */
	int numberOfUser();
	
	/**
	 * 被关注人数前6的用户
	 * @return 用户的list
	 */
	List<User> bestUser();
}
