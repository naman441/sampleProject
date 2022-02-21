package com.naman.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.naman.Model.User;
import com.naman.dao.UserDao;

@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();
	
	@Mock
	private UserDao userDao;
	
	@BeforeAll
	private void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testUserMatch() {
		assertTrue(userService.userMatch(new User("naman", "naman", "customer"), 
				new User("naman", "naman", "customer")));
	}
	
	@Test
	void testUserMatchDifferentName() {
		assertFalse(userService.userMatch(new User("naman", "naman", "customer"), 
				new User("karan", "naman", "customer")));
	}
	
	@Test
	void testUserMatchDifferentPassword() {
		assertFalse(userService.userMatch(new User("naman", "naman", "customer"), 
				new User("naman", "karan", "customer")));
	}
	
	@Test
	void testUserMatchDifferentRole() {
		assertTrue(userService.userMatch(new User("naman", "naman", "customer"), 
				new User("naman", "naman", "admin")));
	}

	@Test
	void testRegisterUser() {
		when(userDao.insert(Mockito.any(User.class))).thenReturn(1);
		
		String val = userService.registerUser(new User("naman", "naman", "admin"));
		assertEquals("login", val);
	}
	
	@Test
	void testRegisterUserFail() {
		when(userDao.insert(Mockito.any(User.class))).thenReturn(0);
		
		String val = userService.registerUser(new User("naman", "naman", "admin"));
		assertEquals("register", val);
	}

	@Test
	void testGetDbUser() {
		when(userDao.getUserByName(Mockito.anyString())).thenReturn(new User("naman", "naman", "admin"));
		
		User u = userService.getDbUser("naman");
		assertNotNull(u);
		assertEquals("admin", u.getRole());
	}
	
	@Test
	void testGetDbUserFail() {
		when(userDao.getUserByName(Mockito.anyString())).thenReturn(null);
		
		User u = userService.getDbUser("naman");
		assertNull(u);
	}

}
