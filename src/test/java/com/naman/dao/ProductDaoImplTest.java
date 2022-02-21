package com.naman.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.naman.Model.Category;
import com.naman.Model.Product;

@TestInstance(Lifecycle.PER_CLASS)
class ProductDaoImplTest {
	
	private List<Product> products;
	
	@InjectMocks
	private ProductDao dao = new ProductDaoImpl();
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Transaction transaction;
	
	@Mock
	private Query query;
	
	@BeforeAll
	private void init() {
		MockitoAnnotations.initMocks(this);
		
		products = new ArrayList<Product>();
		products.add(new Product("eggs", "eggs", 5));
		products.add(new Product("realme x", "realme", 5000));
		
		when(sessionFactory.openSession()).thenReturn(session);
		when(session.beginTransaction()).thenReturn(transaction);
		doNothing().when(session).close();
		
		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		when(query.setParameter(Mockito.anyInt(), Mockito.any())).thenReturn(query);
		when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
		when(query.getResultList()).thenReturn(products);
	}


	@Test
	void testInsert() {
		when(session.save(Mockito.any())).thenReturn(1);
		int val = dao.insert(new Product("eggs", "eggs", 5));
		assertEquals(1, val);
	}

	@Test
	void testGet() {
		Product p = dao.get(1);
		assertNotNull(p);
		assertEquals(5.0, p.getPrice());
	}

	@Test
	void testGetAllProducts() {
		List<Product> p = dao.getAllProducts();
		assertFalse(p.isEmpty());
		assertEquals(2, p.size());
	}

}
