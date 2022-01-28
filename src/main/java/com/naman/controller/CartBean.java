package com.naman.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.naman.Model.CartItem;
import com.naman.Model.Product;
import com.naman.service.CartService;
import com.naman.service.UserService;

@Controller
@SessionScope
public class CartBean {

	private List<CartItem> items;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		items = cartService.getCartItemsByUser();
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public String buy(Product product) {
		this.items = cartService.addItemToCart(items, product);
		return "cart";
	}
	
	public void delete(Product product) {
		this.items = cartService.removeItemFromCart(items, product);
	}
	
	public double total() {
		return cartService.cartTotal(items);
	}
	
	public String logout() {
		cartService.saveCartOnUserLogout(items);
		return userService.userLogout();
	}
	
}
