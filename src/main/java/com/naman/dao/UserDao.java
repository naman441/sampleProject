package com.naman.dao;

import com.naman.Model.User;

public interface UserDao extends BaseDao<User>{
	
	public User getUserByName(String name);
	
	public String validateUser(User user);

}
