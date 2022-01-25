package com.naman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.naman.Model.Product;
import com.naman.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

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
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@PostMapping("/products")
	public String addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return "success";
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return "delete success";
	}
	
	@PutMapping("/products")
	public String updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		return "update success";
	}
	
}
