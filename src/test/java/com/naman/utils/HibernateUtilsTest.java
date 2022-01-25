package com.naman.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.naman.Model.User;

class HibernateUtilsTest {

	private static SessionFactory sessionFactory;
	private Session session;
	
	@BeforeAll
	public static void setup() {
		sessionFactory = HibernateUtils.getSessionFactory();
		System.out.println("Hibernate session factory created");
	}
	
	@AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }
	
	@BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }
	
	@AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }  
	
	public void testCreateUser() {
		session.beginTransaction();
		
		User user = new User("testUser", "test", "admin");
		int id = (Integer) session.save(user);
		
		session.getTransaction().commit();
		assertTrue(id > 0);
	}
	
	@Test
	public void testGetUser() {
		User user = (User) session.find(User.class, 1);
		assertEquals("testUser", user.getName());
	}
	
	

}
