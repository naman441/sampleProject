package com.naman.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.naman.Model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(user);
		t.commit();
		session.close();
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.delete(user);
		t.commit();
		session.close();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.update(user);
		t.commit();
		session.close();
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<User> q = session.createQuery("from User where id= :id");
		q.setParameter("id", id);
		List<User> user = q.getResultList();
		session.close();
		if(user == null || user.isEmpty())
			return null;
		else return user.get(0);
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<User> q = session.createQuery("from User where name= :name");
		q.setParameter("name", name);
		List<User> user = q.getResultList();
		session.close();
		if(user == null || user.isEmpty())
			return null;
		else return user.get(0);
	}

	@Override
	public String validateUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query<User> q = session.createQuery("from User where name= :name and password= :password");
		q.setParameter("name", user.getName());
		q.setParameter("password", user.getPassword());
		List<User> users = q.getResultList();
		session.close();
		if(users == null || users.isEmpty())
			return null;
		else return users.get(0).getRole();
	}
}
