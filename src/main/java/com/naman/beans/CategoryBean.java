package com.naman.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.naman.Model.Category;
import com.naman.dao.CategoryDao;
import com.naman.dao.CategoryDaoImpl;
import com.naman.service.CategoryService;

@Component
@RequestScope
public class CategoryBean {
	
	private Category category;
	private List<Category> categories;
	private List<String> products;
	
	@Autowired
	private CategoryService categoryService;
	
	public CategoryBean() {
		category = new Category();
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categoryService.getCategoryList(categories);
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public String createCategory() {
		categoryService.createCategory(category, products);
		return "admin?faces-redirect=true";
	}

}
