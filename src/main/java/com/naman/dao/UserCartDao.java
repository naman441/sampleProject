package com.naman.dao;

import org.springframework.stereotype.Repository;

import com.naman.Model.UserCart;

@Repository
public interface UserCartDao extends BaseDao<UserCart>{

	public void updateUserCart(UserCart cart1, UserCart cart2);
	
	public void deleteProductFromCart(int id);
}
