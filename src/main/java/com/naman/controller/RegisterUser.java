package com.naman.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.naman.Model.User;
import com.naman.service.UserService;

@Controller
@SessionScope
public class RegisterUser {
	
	private User user;
	@Autowired
	private UserService service;
	
	@PostConstruct
	public void init() {
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String registerUser() {
		return service.registerUser(user);
	}

}
