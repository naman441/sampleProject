package com.naman.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naman.Model.Category;
import com.naman.Model.Product;

@Repository
public class CategoryDaoImpl implements CategoryDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(category);
		t.commit();
		session.close();
	}

	@Override
	public void delete(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.delete(category);
		t.commit();
		session.close();
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.update(category);
		t.commit();
		session.close();
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<Category> q = session.createQuery("from Category where id= :id");
		q.setParameter("id", id);
		List<Category> categories = q.getResultList();
		session.close();
		if(categories == null || categories.isEmpty())
			return null;
		else return categories.get(0);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<Category> q = session.createQuery("from Category");
		List<Category> categories = q.getResultList();
		session.close();
		return categories;
	}

	@Override
	public List<Product> getProductByCategory(int id) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		Session session =  sessionFactory.openSession();
		Query<Category> q = session.createQuery("from Category where id = :id");
		q.setParameter("id", id);
		List<Category> categories = q.getResultList();
		if(categories == null || categories.isEmpty())
			return products;
		else {
			Category c = categories.get(0);
			products = c.getProducts();
			return products;
		}
	}
}
