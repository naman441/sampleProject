package com.naman.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.naman.Model.CartItem;
import com.naman.Model.User;
import com.naman.dao.UserDao;
import com.naman.service.CartService;
import com.naman.service.UserService;

@Controller
@RequestScope
public class UserLogin {
	
	private String userMsg;
	private User user;
	
	@Autowired
	UserService service;
	
	@Autowired
	CartService cartService;
	
	@PostConstruct
	public void init() {
		user = new User();
		userMsg = "";
	}
	
	public String getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String validateUser() {
		String result = service.validateUser(user);
		setUser(service.getDbUser(user.getName()));
		return result;
	}
	
	public String userLogout() {
		return service.userLogout();
	}
	
	public String registerUser() {
		return service.registerUser(user);
	}
	
}
