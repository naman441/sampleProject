package com.naman.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naman.Model.Product;
import com.naman.Model.UserCart;

@Repository
public class UserCartDaoImpl implements UserCartDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(UserCart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(cart);
		t.commit();
		session.close();
	}

	@Override
	public void delete(UserCart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.delete(cart);
		t.commit();
		session.close();
	}

	@Override
	public void update(UserCart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.update(cart);
		t.commit();
		session.close();
	}

	@Override
	public UserCart get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<UserCart> q = session.createQuery("from UserCart where id= :id");
		q.setParameter("id", id);
		List<UserCart> products = q.getResultList();
		session.close();
		if(products == null || products.isEmpty())
			return null;
		else return products.get(0);
	}
	
	public void deleteProductFromCart(int id) {
		Session session = sessionFactory.openSession();
		Query q = session.createSQLQuery("delete from USERCART_ITEMS where product_id = :id");
		q.setParameter("id", id);
		Transaction t = session.beginTransaction();
		q.executeUpdate();
		t.commit();
		session.close();
	}

}
