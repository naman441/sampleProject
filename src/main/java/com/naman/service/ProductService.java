package com.naman.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.dao.CategoryDaoImpl;
import com.naman.dao.ProductDaoImpl;
import com.naman.dao.UserCartDaoImpl;

@Service
public class ProductService {
	
	@Autowired
	ProductDaoImpl productDaoImpl;
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	@Autowired
	UserCartDaoImpl userCartDaoImpl;
	
	public void addProduct(Product product) {
		productDaoImpl.insert(product);
	}
	
	public List<Product> getAllProducts() {
		return productDaoImpl.getAllProducts();
	}
	
	public List<Product> getProductList(List<Product> products){
			return getAllProducts();
	}
	
	public List<Product> getProductsByCategory(int id) {
		return categoryDaoImpl.getProductByCategory(id);
	}
	
	public void createProduct(Product product, List<String> categories) {
		Set<Category> list = new HashSet<Category>();
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
	
	public Product getProductById(int id) {
		return productDaoImpl.get(id);
	}
	
	public void deleteProduct(int id) {
		Product p =  productDaoImpl.get(id);
		userCartDaoImpl.deleteProductFromCart(p.getId());
		productDaoImpl.delete(p);
	}
	
	public void updateProduct(Product product) {
		productDaoImpl.update(product);
	}

}
