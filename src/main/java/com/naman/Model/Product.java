package com.naman.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id", unique = true, nullable = false)
	private int id;
	
	@Column(name="product_name", unique = true, nullable = false)
	private String name;
	@Column(name="product_desc")
	private String desc;
	@Column(name="product_price")
	private double price;
	
	@ManyToMany(cascade = {CascadeType.DETACH, 
						   CascadeType.MERGE,
						   CascadeType.PERSIST,
						   CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(
				name = "Product_Category",
				joinColumns = {@JoinColumn(name="product_id", referencedColumnName = "product_id")},
				inverseJoinColumns = {@JoinColumn(name="category_id", referencedColumnName = "category_id")}
			)
	private Set<Category> categories = new HashSet<Category>();
	
	public Product() {
	}
	
	public Product(String name, String desc, double price) {
		this.name = name;
		this.desc = desc;
		this.price = price;
	}
	
	public Product(String name, String desc, double price, Set<Category> categories) {
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.categories = categories;
	}
	
	public Product(int id, String name, String desc, double price) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}
