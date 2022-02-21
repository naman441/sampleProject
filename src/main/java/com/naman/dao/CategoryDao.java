package com.naman.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.naman.Model.Category;
import com.naman.Model.Product;

@Repository
public interface CategoryDao extends BaseDao<Category>{

	public List<Category> getAllCategory();
	
	public List<Product> getProductByCategory(int id); 
	
	public Category getCategoryByName(String name);
}
