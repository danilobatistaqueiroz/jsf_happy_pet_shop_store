package com.labs.jsf.model;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ItemCart {
	private Product item;
	private int quantity;

	public ItemCart(){

	}
	public ItemCart(Product product, int quantity){
		this.item = new Product(product.getId(),product.getName(),product.getDescription(),product.getPrice());
		this.quantity = quantity;
	}
	public ItemCart(long id, String name, String description, double price, int quantity){
		this.item = new Product(id,name,description,price);
		this.quantity = quantity;
	}

	public int sum(int quantity){
		System.out.println("sum:"+quantity);
		this.quantity+=quantity;
		System.out.println("quantity:"+this.quantity);
		return this.quantity;
	}

	public long getId(){
		return item.getId();
	}
	public void setId(long id){
		this.item.setId(id);
	}
	public String getName(){
		return item.getName();
	}
	public void setName(String name){
		this.item.setName(name);
	}
	public String getDescription(){
		return this.item.getDescription();
	}
	public void setDescription(String description){
		this.item.setDescription(description);
	}
	public double getPrice(){
		return item.getPrice();
	}
	public void setPrice(double price){
		this.item.setPrice(price);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		System.out.println("changing the quantity:"+quantity);
		this.quantity = quantity;
	}

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}

}
