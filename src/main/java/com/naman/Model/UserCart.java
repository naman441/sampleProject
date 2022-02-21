package com.naman.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserCart {
	
	@Id
	private int id;
	
	@OneToOne(cascade = {CascadeType.DETACH, 
		   				  CascadeType.MERGE,
		   				  CascadeType.PERSIST,
		   				  CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name ="users_id")
	@MapsId
	private User user;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<CartItem> items;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

}
