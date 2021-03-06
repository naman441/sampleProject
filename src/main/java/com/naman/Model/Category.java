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
	@Column(name="category_id", unique = true, nullable = false)
	private int id;
	
	@Column(name="category_name", unique = true, nullable = false)
	private String name;
	@Column(name="category_desc")
	private String desc;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.DETACH, 
		   					CascadeType.MERGE,
		   					CascadeType.PERSIST,
		   					CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<Product> products = new HashSet<Product>();
	
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Category)) {
			return false;
		}
		Category other = (Category) object;
		if(this.id == other.id)
			return true;
		else return false;
	}
}
