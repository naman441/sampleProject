package com.naman.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naman.Model.Category;
import com.naman.Model.Product;
import com.naman.Model.User;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insert(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		int id = (int) session.save(product);
		t.commit();
		session.close();
		return id;
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
		Query<Product> q = session.createQuery("from Product where product_id= :id");
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

}
