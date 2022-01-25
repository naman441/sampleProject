package com.naman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.service.CategoryService;
import com.naman.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/products")
	public List<Product> getProduct() {
		List<Product> list = new ArrayList<Product>();
		list = productService.getAllProducts();
		return list;
	}
	
	@GetMapping(produces = "application/json", value = "/products/{id}")
	public Product getProduct(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@PostMapping("/products")
	public String addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return "success";
	}
	
	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable int id) {
		return categoryService.getCategoryById(id);
	}
	
}
