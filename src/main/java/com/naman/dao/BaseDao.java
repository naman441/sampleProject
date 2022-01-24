package com.naman.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao<T> {
	
	public void insert(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T get(int id);

}
