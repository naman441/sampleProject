package com.naman.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
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
class CategoryDaoImplTest {
	
	private List<Category> categories; 
	
	@InjectMocks
	private CategoryDao categoryDao = new CategoryDaoImpl();
	
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
		
		categories = new ArrayList<Category>();
		categories.add(new Category("Electronics", "electric"));
		categories.add(new Category("Dairy", "daily"));
		
		Set<Product> pr = new HashSet<Product>();
		pr.add(new Product("eggs", "eggs", 5));
		categories.get(0).setProducts(pr);
		
		when(sessionFactory.openSession()).thenReturn(session);
		when(session.beginTransaction()).thenReturn(transaction);
		doNothing().when(session).close();
		
		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		when(query.setParameter(Mockito.anyInt(), Mockito.any())).thenReturn(query);
		when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
		when(query.getResultList()).thenReturn(categories);
	}

	@Test
	void testInsert() {
		when(session.save(Mockito.any())).thenReturn(1);
		int val = categoryDao.insert(new Category("dairy", "d"));
		assertEquals(1, val);
	}

	@Test
	void testGet() {
		Category c = categoryDao.get(1);
		assertNotNull(c);
		assertEquals("electric", c.getDesc());
	}

	@Test
	void testGetAllCategory() {
		List<Category> c1 = categoryDao.getAllCategory();
		assertFalse(c1.isEmpty());
		assertEquals(2, c1.size());
	}
	
	@Test
	void testGetProductByCategory() {
		List<Product> p = categoryDao.getProductByCategory(1);
		assertFalse(p.isEmpty());
		assertEquals("eggs", p.get(0).getName());
	}

	@Test
	void testGetCategoryByName() {
		Category c = categoryDao.getCategoryByName("Electronics");
		assertNotNull(c);
		assertEquals("electric", c.getDesc());
	}

}
