package com.naman.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.dao.CategoryDaoImpl;
import com.naman.dao.ProductDaoImpl;

@Service
public class ProductService {
	
	@Autowired
	ProductDaoImpl productDaoImpl;
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	public void addProduct(Product product) {
		productDaoImpl.insert(product);
	}
	
	public List<Product> getAllProducts() {
		return productDaoImpl.getAllProducts();
	}
	
	public List<Product> getProductList(List<Product> products){
		if(products != null && !products.isEmpty())
			return products;
		else
			return getAllProducts();
	}
	
	public List<Product> getProductsByCategory(int id) {
		return categoryDaoImpl.getProductByCategory(id);
	}
	
	public void createProduct(Product product, List<String> categories) {
		List<Category> list = new ArrayList<Category>();
		if(categories != null && !categories.isEmpty()) {
			for(String n : categories) {
				int id = Integer.parseInt(n);
				Category c = categoryDaoImpl.get(id);
				list.add(c);
			}
		}
		product.setCategories(list);
		productDaoImpl.insert(product);
	}

}
