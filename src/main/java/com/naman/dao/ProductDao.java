package com.naman.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.naman.Model.Product;

@Repository
public interface ProductDao extends BaseDao<Product> {
	
	public List<Product> getAllProducts();
	
}
