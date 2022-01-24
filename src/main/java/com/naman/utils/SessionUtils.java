package com.naman.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionUtils {
	
	private static HttpSession httpSession;

	public static HttpSession getHttpSession() {
		FacesContext con = 	FacesContext.getCurrentInstance();
		ExternalContext con1 = con.getExternalContext();
		httpSession = (HttpSession) con1.getSession(false);
		return httpSession;
	}
}
