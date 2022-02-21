package com.naman.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.naman.dao.CategoryDao;
import com.naman.dao.ProductDao;

@TestInstance(Lifecycle.PER_CLASS)
class ProductServiceTest {
	
	List<Product> products;
	
	@InjectMocks
	private ProductService productService = new ProductService();
	
	@Mock
	private ProductDao productDao;
	
	@Mock
	private CategoryDao categoryDao;
	
	@BeforeAll
	private void init() {
		
		MockitoAnnotations.initMocks(this);
		products = new ArrayList<Product>();
		products.add(new Product("Ball", "balls", 5));
		products.add(new Product("Eggs", "egg", 7));
		
	}
	@Test
	void testAddProduct() {
		when(productDao.insert(Mockito.any(Product.class))).thenReturn(1);
		assertEquals(1, productService.addProduct(new Product("Ball", "balls", 1)));
	}

	@Test
	void testGetAllProducts() {
		when(productDao.getAllProducts()).thenReturn(products);
		List<Product> products1 = productService.getAllProducts();
		
		assertFalse(products1.isEmpty());
		
		assertEquals(2, products1.size());
	}

	@Test
	void testGetProductsByCategory() {
		when(categoryDao.getProductByCategory(Mockito.anyInt())).thenReturn(products);
		
		List<Product> products1 = productService.getProductsByCategory(1);
		assertFalse(products1.isEmpty());
		assertEquals(2, products1.size());
	}

	@Test
	void testCreateProduct() {
		when(productDao.insert(Mockito.any(Product.class))).thenReturn(1);
		
		int val = productService.createProduct(new Product("shampoo", "no-tears", 174), new ArrayList<Category>());
		assertEquals(1, val);
	}

	@Test
	void testGetProductById() {
		when(productDao.get(Mockito.anyInt())).thenReturn(new Product("shampoo", "no-tears", 174));
		
		Product p = productService.getProductById(1);
		assertNotNull(p);
		assertEquals("shampoo", p.getName());
	}

}
