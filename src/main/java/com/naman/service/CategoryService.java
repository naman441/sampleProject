package com.naman.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.dao.CategoryDao;
import com.naman.dao.CategoryDaoImpl;
import com.naman.dao.ProductDaoImpl;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategory();
	}
	
	public int addCategory(Category category) {
		return categoryDao.insert(category);
	}
	
	public List<Category> getCategoryList(List<Category> categories){
		if(categories != null && categories.size() > 0)
			return categories;
		else return categoryDao.getAllCategory();
	}
	
	public Category getCategoryById(int id) {
		return categoryDao.get(id);
	}
	
	public void deleteCategory(int id) {
		Category c =  categoryDao.get(id);
		categoryDao.delete(c);
	}
	
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

}
