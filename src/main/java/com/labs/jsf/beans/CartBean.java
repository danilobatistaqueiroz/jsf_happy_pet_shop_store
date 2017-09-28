package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.labs.jsf.model.ItemCart;
import com.labs.jsf.model.Product;

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
	
	public void update(Product product, int qtd) {
		long itemId = product.getId();
		ItemCart pc = itemList.stream().
				filter(p -> p.getItem().getId() == itemId).
				findFirst().orElseGet(()->addItem(product,0));
		if(pc.getQuantity()==0 && qtd<=0) {
			itemList.removeIf(p -> p.getItem().getId() == itemId);
		} else {
			pc.setQuantity(pc.getQuantity() + qtd);
			FacesContext context = FacesContext.getCurrentInstance();
	       	context.addMessage(null, new FacesMessage("Successful",  "Your message: yes") );
		}
	}

	public ItemCart addItem(Product product, int qtd){
		ItemCart i = new ItemCart(product, qtd);
		itemList.add(i);
		return i;
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