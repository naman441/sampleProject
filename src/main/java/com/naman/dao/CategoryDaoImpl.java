package com.naman.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naman.Model.Category;
import com.naman.Model.Product;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		int id = (int) session.save(category);
		t.commit();
		session.close();
		return id;
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
		Query<Category> q = session.createQuery("from Category where category_id= :id");
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
		Query<Category> q = session.createQuery("from Category where category_id = :id");
		q.setParameter("id", id);
		List<Category> categories = q.getResultList();
		if(categories == null || categories.isEmpty())
			return products;
		else {
			Category c = categories.get(0);
			products.addAll(c.getProducts());
			return products;
		}
	}
	
	public Category getCategoryByName(String name) {
		Session session = sessionFactory.openSession();
		Query<Category> q = session.createQuery("from Category where category_name= :name");
		q.setParameter("name", name);
		List<Category> categories = q.getResultList();
		session.close();
		if(categories == null || categories.isEmpty())
			return null;
		else return categories.get(0);
	}
}
