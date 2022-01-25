package com.naman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naman.Model.CartItem;
import com.naman.Model.Product;
import com.naman.Model.User;
import com.naman.Model.UserCart;
import com.naman.dao.UserCartDaoImpl;
import com.naman.dao.UserDaoImpl;
import com.naman.utils.SessionUtils;

@Service
public class CartService {
	
	@Autowired
	private UserCartDaoImpl userCartDaoImpl;
	
	@Autowired
	private UserDaoImpl userDaoImpl;

	public void insertCart(UserCart cart) {
		userCartDaoImpl.insert(cart);
	}
	
	public List<CartItem> addItemToCart(List<CartItem> items, Product product) {
		int index = -1;
		if(items != null && items.size() > 0)
		for(int i=0;i<items.size();i++) 
			if(items.get(i).getProduct().getId() == product.getId())
				index = i;
		
		if(index == -1) {
			items.add(new CartItem(product, 1.0));
		}
		else {
			items.get(index).setQuantity(items.get(index).getQuantity() + 1);
		}
		return items;
	}
	
	public List<CartItem> removeItemFromCart(List<CartItem> items, Product product) {
		int index = IntStream.range(0, items.size())
				.filter(i-> items.get(i).getProduct().getId() == product.getId()).findFirst().getAsInt();
		if(items.get(index).getQuantity() > 1)
			items.get(index).setQuantity(items.get(index).getQuantity() - 1);
		else
			items.remove(index);
		return items;
	}
	
	public double cartTotal(List<CartItem> items) {
		double sum = 0;
		sum = items.stream().collect(Collectors.summingDouble(p-> p.getProduct().getPrice()*p.getQuantity()));
		return sum;
	}
	
	public void saveCartOnUserLogout(List<CartItem> items) {
		UserCart cart = new UserCart();
		String name = (String) SessionUtils.getHttpSession().getAttribute("userName");
		User user = userDaoImpl.getUserByName(name);
		if(user != null)
			cart.setUser(user);
		cart.setItems(items);
		UserCart uCart = userCartDaoImpl.get(user.getId());
		if(items != null && !items.isEmpty()) {
			if(uCart == null)
				userCartDaoImpl.insert(cart);
			else {
				userCartDaoImpl.delete(uCart);
				userCartDaoImpl.insert(cart);
			}
		}
		else {
			if(uCart != null)
				userCartDaoImpl.delete(uCart);
		}
	}
	
	public List<CartItem> getCartItemsByUser(List<CartItem> items) {
		if(items == null || items.isEmpty()) {
			String name = (String) SessionUtils.getHttpSession().getAttribute("userName");
			User user = userDaoImpl.getUserByName(name);
			if(user != null) {
				UserCart cart = userCartDaoImpl.get(user.getId());
				if(cart != null)
					items = cart.getItems();
				else 
					items = new ArrayList<CartItem>();
			}
		}
		return items;
	}
	
}
