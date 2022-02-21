package com.naman.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import com.naman.Model.CartItem;
import com.naman.Model.Category;
import com.naman.dao.CategoryDao;

@TestInstance(Lifecycle.PER_CLASS)
class CategoryServiceTest {
	
	private List<Category> categories;
	
	@InjectMocks
	private CategoryService categoryService = new CategoryService();
	
	@Mock
	private CategoryDao categoryDaoMock;
	
	@BeforeAll
	private void init() {
		MockitoAnnotations.initMocks(this);
		
		categories = new ArrayList<Category>();
		categories.add(new Category("Electronics", "electric"));
		categories.add(new Category("Dairy", "daily"));
		
	}

	@Test
	void testGetAllCategories() {
		when(categoryDaoMock.getAllCategory()).thenReturn(categories);
		
		List<Category> categories1 = categoryService.getAllCategories();
		assertEquals(2, categories1.size());
	}

	@Test
	void testAddCategory() {
		when(categoryDaoMock.insert(Mockito.any(Category.class))).thenReturn(1);
		assertEquals(1, categoryService.addCategory(new Category("games", "games")));
	}

	@Test
	void testGetCategoryList() {
		assertFalse(categories.isEmpty());
		int val = categoryService.getCategoryList(categories).size();
		assertEquals(2, val);
	}
	
	@Test
	void testGetCategoryListWhenEmpty() {
		when(categoryDaoMock.getAllCategory()).thenReturn(new ArrayList<Category>());
		
		int val = categoryService.getCategoryList(new ArrayList<Category>()).size();
		assertEquals(0, val);
	}

	@Test
	void testGetCategoryById() {
		when(categoryDaoMock.get(Mockito.anyInt())).thenReturn(new Category("Sports", "games"));
		Category c = categoryService.getCategoryById(1);
		assertNotNull(c);
		assertEquals("Sports", c.getName()); 
	}
}
