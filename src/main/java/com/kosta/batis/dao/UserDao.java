package com.kosta.batis.dao;

import com.kosta.batis.domain.User;


public interface UserDao {
	
	int deleteUser(String id);
	
	User selectuser(String id);
	
	int insertUser(User user);
	
	int updateUser(User user);
	
	void deleteAll() throws Exception;
	
}
