package com.naman.utils;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login.jsf").permitAll().failureUrl("/login.jsf?error=true");
		http.logout().logoutSuccessUrl("/login.jsf");
	}

}
