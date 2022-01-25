package com.naman.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public void createCategory(Category category, List<String> products) {
		List<Product> list = new ArrayList<Product>();
		if(products != null && !products.isEmpty()) {
			for(String n : products) {
				int id = Integer.parseInt(n);
				Product p = productDaoImpl.get(id);
				list.add(p);
			}
		}
		category.setProducts(list);
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

}
