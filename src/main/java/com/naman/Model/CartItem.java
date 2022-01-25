package com.naman.Model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class CartItem {

	@OneToOne(fetch = FetchType.EAGER)
	private Product product;
	
	private double quantity;
	
	public CartItem() {
	}
	
	public CartItem(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
