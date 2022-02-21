package com.naman.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.naman.Model.Product;
import com.naman.Model.User;

@TestInstance(Lifecycle.PER_CLASS)
class UserDaoImplTest {
	
	private List<User> users;
	
	@InjectMocks
	private UserDao dao = new UserDaoImpl();
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Transaction transaction;
	
	@Mock
	private Query query;
	
	@BeforeAll
	private void init() {
		MockitoAnnotations.initMocks(this);
		
		users = new ArrayList<User>();
		users.add(new User("naman", "naman", "admin"));
		users.add(new User("karan", "karan", "customer"));
		
		when(sessionFactory.openSession()).thenReturn(session);
		when(session.beginTransaction()).thenReturn(transaction);
		doNothing().when(session).close();
		
		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		when(query.setParameter(Mockito.anyInt(), Mockito.any())).thenReturn(query);
		when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
		
	}

	@Test
	void testInsert() {
		when(session.save(Mockito.any())).thenReturn(1);
		
		assertEquals(1, dao.insert(new User("naman", "naman", "admin")));
	}

	@Test
	void testGet() {
		when(query.getResultList()).thenReturn(users);
		
		User u = dao.get(1);
		assertNotNull(u);
		assertEquals("admin", u.getRole());
	}

	@Test
	void testGetUserByName() {
		when(query.getResultList()).thenReturn(users);
		
		User u = dao.getUserByName("naman");
		assertNotNull(u);
		assertEquals("admin", u.getRole());
	}

	@Test
	void testValidateUser() {
		when(query.getResultList()).thenReturn(users);
		
		String role = dao.validateUser(new User("naman", "naman", "admin"));
		assertEquals("admin", role);
	}
	
	@Test
	void testValidateUserFail() {
		when(query.getResultList()).thenReturn(new ArrayList<User>());
		
		String role = dao.validateUser(new User("naman", "karan", "admin"));
		assertNull(role);
	}

}
