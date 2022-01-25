package com.naman.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.Model.User;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(product);
		t.commit();
		session.close();
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.delete(product);
		t.commit();
		session.close();
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.update(product);
		t.commit();
		session.close();
	}

	@Override
	public Product get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<Product> q = session.createQuery("from Product where id= :id");
		q.setParameter("id", id);
		List<Product> products = q.getResultList();
		session.close();
		if(products == null || products.isEmpty())
			return null;
		else return products.get(0);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<Product> q = session.createQuery("from Product");
		List<Product> products = q.getResultList();
		session.close();
		return products;
	}

	@Override
	public List<Product> getProductByCategory(int id) {
		// TODO Auto-generated method stub
//		Session session = sessionFactory.openSession();
//		Query<Product> q = session.createQuery("from Product where = :id");
//		q.setParameter("id", id);
//		List<Product> products = q.getResultList();
//		session.close();
//		if(products == null || products.isEmpty())
//			return null;
//		else return products.get(0);
		return null;
	}

}
