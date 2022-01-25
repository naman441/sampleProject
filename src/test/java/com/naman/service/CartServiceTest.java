package com.naman.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;

import com.naman.Model.CartItem;
import com.naman.Model.Product;

import junit.framework.TestCase;

@TestInstance(Lifecycle.PER_CLASS)
public class CartServiceTest extends TestCase {
	
	private List<CartItem> items;
	
	private CartService service = new CartService();
	
	@BeforeAll
	public void createTestList() {
		List<CartItem> items = new ArrayList<CartItem>();
		items.add(new CartItem(new Product(1, "Apples", "", 4), 1));
		items.add(new CartItem(new Product(2, "oranges", "", 3), 2));
		items.add(new CartItem(new Product(3, "oneplus 9rt", "6gb 128gb", 14300), 1));
		this.items = items;
	}
	
	@Test
	public void testTotal() {
		double sum = service.cartTotal(items);
		double sumTest = 0;
		for(CartItem item : items)
			sumTest += item.getProduct().getPrice()*item.getQuantity();
		assertEquals(sumTest, sum);
	}
	
	@Test
	public void testListAddition() {
		this.items = service.addItemToCart(items, new Product(4, "Sugar", "", 45));
		assertEquals(4, items.size());
	}
	
	@Test
	public void testListAdditionInSameObject() {
		this.items = service.addItemToCart(items, new Product(2, "oranges", "", 3));
		double val = items.stream().filter(p->p.getProduct().getId() == 2)
				.mapToDouble(p->p.getQuantity()).toArray()[0];
		assertEquals(3.0, val);
	}
	
	@Test
	public void testDeletionFromList() {
		this.items = service.removeItemFromCart(items, new Product(2, "oranges", "", 3));
		double val = items.stream().filter(p->p.getProduct().getId() == 2)
				.mapToDouble(p->p.getQuantity()).toArray()[0];
		assertEquals(2.0, val);
	}

}
