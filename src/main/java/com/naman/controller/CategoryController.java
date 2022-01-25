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
import org.springframework.web.bind.annotation.RestController;
import com.naman.Model.Category;
import com.naman.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories/{id}")
	public Category getCategory(@PathVariable int id) {
		return categoryService.getCategoryById(id);
	}
	
	@GetMapping("/categories")
	public List<Category> getCategory() {
		List<Category> list = new ArrayList<Category>();
		list = categoryService.getAllCategories();
		return list;
	}
	
	@PostMapping("/categories")
	public String addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
		return "success";
	}
	
	@DeleteMapping("/categories/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return "delete success";
	}
	
	@PutMapping("/categories")
	public String updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
		return "update success";
	}
}
