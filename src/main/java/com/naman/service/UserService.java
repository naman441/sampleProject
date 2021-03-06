package com.naman.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naman.Model.User;
import com.naman.dao.UserDao;
import com.naman.dao.UserDaoImpl;
import com.naman.utils.AppUtils;
import com.naman.utils.SessionUtils;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public String validateUser(User user) {
		User userDb = userDao.getUserByName(user.getName());
		if(userDb == null) {
			SessionUtils.getHttpSession().setAttribute("userName", user.getName());
			return "register";
		}
		else if(userMatch(userDb, user)) {
			SessionUtils.getHttpSession().setAttribute("userName", user.getName());
			userDb.setTimeStamp(AppUtils.formatDate(LocalDateTime.now()));
			userDao.update(userDb);
			return userDb.getRole();
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Value", "Please check name"));
			return "login";
		}
	}
	
	public String userLogout() {
		SessionUtils.getHttpSession().invalidate();
		return "login";
	}
	
	public boolean userMatch(User user1, User user2) {
		if(user1.getName().equalsIgnoreCase(user2.getName()) && 
				user1.getPassword().equals(user2.getPassword())) {
			return true;
		}
		else return false;
	}
	
	public String registerUser(User user) {
		int val = userDao.insert(user);
		if(val > 0)
			return "login";
		else 
			return "register";
	}
	
	public User getDbUser(String name) {
		return userDao.getUserByName(name);
	}

}
