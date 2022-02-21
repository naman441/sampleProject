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
import com.naman.dao.CategoryDao;
import com.naman.dao.CategoryDaoImpl;
import com.naman.dao.ProductDao;
import com.naman.dao.ProductDaoImpl;
import com.naman.dao.UserCartDao;
import com.naman.dao.UserCartDaoImpl;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserCartDao userCartDao;
	
	public int addProduct(Product product) {
		return productDao.insert(product);
	}
	
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
//	public List<Product> getProductList(List<Product> products){
//			return getAllProducts();
//	}
	
	public List<Product> getProductsByCategory(int id) {
		return categoryDao.getProductByCategory(id);
	}
	
	public int createProduct(Product product, List<Category> categories) {
		Set<Category> list = new HashSet<Category>();
		for(Category c : categories)
			list.add(c);
		product.setCategories(list);
		return addProduct(product);
	}
	
	public Product getProductById(int id) {
		return productDao.get(id);
	}
	
	public void deleteProduct(int id) {
		Product p =  productDao.get(id);
		userCartDao.deleteProductFromCart(p.getId());
		productDao.delete(p);
	}
	
	public void updateProduct(Product product) {
		productDao.update(product);
	}

}
