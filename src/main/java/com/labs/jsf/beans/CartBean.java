package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.labs.jsf.model.ItemCart;

@Named
@ViewScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 8461784989288791886L;

	private List<ItemCart> itemList = new ArrayList<>();

	public List<ItemCart> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemCart> itemList) {
		this.itemList = itemList;
	}

	@PostConstruct
	public void init(){
	}

	public void addItem(String name, String description, double price, int quantity){
		ItemCart i = new ItemCart(1,name,description,price,quantity);
		itemList.add(i);
	}

	public void addItem(ItemCart item){
		ItemCart i = itemList.stream().findFirst().orElse(item);
		itemList.add(i);
	}

	public void removeItem(ItemCart item){
		System.out.println("deleting the item:"+item.getId());
		itemList.remove(item);
	}
	public double getTotalOrderValue() {
		return itemList.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
	}

}