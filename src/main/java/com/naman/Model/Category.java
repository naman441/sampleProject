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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;
	
	@Column(name="name", unique = true, nullable = false)
	private String name;
	private String desc;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.DETACH, 
		   					CascadeType.MERGE,
		   					CascadeType.PERSIST,
		   					CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(
				name = "Product_Category",
				joinColumns = {@JoinColumn(name="category_id")},
				inverseJoinColumns = {@JoinColumn(name="product_id")}
			)
	private List<Product> products = new ArrayList<Product>();
	
	public Category() {
	}
	
	public Category(String name, String desc) {
		this.name = name;
		this.desc = desc;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
