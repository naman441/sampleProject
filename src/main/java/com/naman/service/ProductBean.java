package com.naman.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.dao.CategoryDao;
import com.naman.dao.CategoryDaoImpl;
import com.naman.dao.ProductDao;
import com.naman.dao.ProductDaoImpl;

@Component
@RequestScope
public class ProductBean {

	private List<Product> products;
	private Category selectedCategory;
	private Product product;
	private List<String> categories;
	
	@Autowired
	private ProductService productService;
	
	public ProductBean() {
		this.product = new Product();
	}

	public List<Product> getProducts() {
		return  productService.getProductList(products);
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String index() {
		return "customer";
	}
	
	public String toCart() {
		return "cart";
	}
	
	public String selectCategory(Category category) {
		this.products = productService.getProductsByCategory(category.getId());
		this.selectedCategory = category;
		return "customer";
	}
	
	public String createProduct() {
		productService.createProduct(product, categories);
		return "admin?faces-redirect=true";
	}
}
