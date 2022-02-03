package com.naman.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.dao.CategoryDaoImpl;
import com.naman.dao.ProductDaoImpl;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDaoImpl categoryDaoImpl;
	
	@Autowired
	private ProductDaoImpl productDaoImpl;
	
	public List<Category> getAllCategories() {
		return categoryDaoImpl.getAllCategory();
	}
	
	public void addCategory(Category category) {
		categoryDaoImpl.insert(category);
	}
	
	public void createCategory(Category category) {
		categoryDaoImpl.insert(category);
	}
	
	public List<Category> getCategoryList(List<Category> categories){
		if(categories != null && categories.size() > 0)
			return categories;
		else return categoryDaoImpl.getAllCategory();
	}
	
	public Category getCategoryById(int id) {
		return categoryDaoImpl.get(id);
	}
	
	public void deleteCategory(int id) {
		Category c =  categoryDaoImpl.get(id);
		categoryDaoImpl.delete(c);
	}
	
	public void updateCategory(Category category) {
		categoryDaoImpl.update(category);
	}

}
