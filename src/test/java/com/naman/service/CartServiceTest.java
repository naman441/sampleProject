package com.naman.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.MockitoCore;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.naman.Model.CartItem;
import com.naman.Model.Product;
import com.naman.Model.User;
import com.naman.Model.UserCart;
import com.naman.dao.UserCartDaoImpl;
import junit.framework.TestCase;

@TestInstance(Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest extends TestCase {
	
	private List<CartItem> items;
	
	@Mock
	private CartService service;
	
	@InjectMocks
	private UserCartDaoImpl impl;
	
	@Mock
	UserCart cart;
	
	@Mock
	User user;
	
	@BeforeAll
	public void createTestList() {
		
		MockitoAnnotations.initMocks(this);
		
		List<CartItem> items = new ArrayList<CartItem>();
		items.add(new CartItem(new Product(1, "Apples", "", 4), 1));
		items.add(new CartItem(new Product(2, "oranges", "", 3), 2));
		items.add(new CartItem(new Product(3, "oneplus 9rt", "6gb 128gb", 14300), 1));
		this.items = items;
		
		impl = mock(UserCartDaoImpl.class);
		service = new CartService(impl);
		
		user = new User("naman", "naman", "admin");
		cart = new UserCart();
		cart.setUser(user);
		cart.setId(1);
	}
	
	@Test
	public void testInsert() {
		when(impl.insert(cart)).thenReturn(1);
		assertEquals(service.insertCart(cart), 1);
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
	
	@Test
	public void testUpdateItemQuantity() {
		this.items = service.updateItemQuantity(items, new Product(2, "oranges", "", 3), 5);
		double val = items.stream().filter(p->p.getProduct().getId() == 2)
				.mapToDouble(p->p.getQuantity()).toArray()[0];
		assertEquals(5.0, val);
	}
}
