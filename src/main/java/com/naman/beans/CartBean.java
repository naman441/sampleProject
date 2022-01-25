package com.naman.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.naman.Model.CartItem;
import com.naman.Model.Product;
import com.naman.service.CartService;
import com.naman.service.UserService;

@Component
@SessionScope
public class CartBean {

	private List<CartItem> items;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	public CartBean() {
		this.items = new ArrayList<CartItem>();
	}

	public List<CartItem> getItems() {
		this.items = cartService.getCartItemsByUser(items);
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public String buy(Product product) {
		this.items = cartService.addItemToCart(items, product);
		return "cart";
	}
	
	public String delete(Product product) {
		this.items = cartService.removeItemFromCart(items, product);
		return "cart";
	}
	
	public double total() {
		return cartService.cartTotal(items);
	}
	
	public String logout() {
		cartService.saveCartOnUserLogout(items);
		return userService.userLogout();
	}
	
}
