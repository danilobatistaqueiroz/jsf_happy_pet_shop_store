package com.labs.jsf.beans;

import java.util.List;
import com.labs.jsf.model.ProductCart;

public class ShopCartBean {
	private String username;
	private List<ProductCart> productsCart;
	private double totalOrderValue;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<ProductCart> getProductsCart() {
		return productsCart;
	}
	public void setProductsCart(List<ProductCart> productsCart) {
		this.productsCart = productsCart;
	}
	public double getTotalOrderValue() {
		return totalOrderValue;
	}
	public void setTotalOrderValue(double totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}
}
