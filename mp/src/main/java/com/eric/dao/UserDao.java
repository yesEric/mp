package com.eric.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eric.dom.User;

public interface UserDao extends GenericDao<User, Long>  {
	
	/**
	 * 根据用户名获取用户的方法.
	 * @param userName 用户名
	 * @return 用户对象
	 */
	@Transactional
	User loadUserByUserName(String userName);
	/**
	 * 获取所有用户的方法.
	 * @return 所有用户
	 */
	List<User> getUsers();
	/**
	 * 保存用户的方法.
	 * @param user 用户对象
	 */
	User saveUser(User user);
	

}
