package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import com.labs.jsf.dao.ProductDAO;
import com.labs.jsf.model.ItemCart;

@SessionScoped
@ManagedBean
public class ShopCartBean implements Serializable {

	private static final long serialVersionUID = 4730813081157767208L;

	@Inject
	private ProductDAO productDAO;

	private String username;
	private List<ItemCart> itemList;
	private double totalOrderValue;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<ItemCart> getItemList() {
		return itemList;
	}
	public void setProductsCart(List<ItemCart> itemList) {
		this.itemList = itemList;
	}
	public double getTotalOrderValue() {
		return totalOrderValue;
	}
	public void setTotalOrderValue(double totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}

	//	public void update(long itemId) {
	//		update(itemId, 1);
	//	}
	//	public void update(long itemId, int qtd) {
	//		ItemCart pc = itemList.stream().
	//				filter(p -> p.getItem().getId() == itemId).
	//				findFirst().orElse(addItem(itemId));
	//		if(pc.getQuantity()==0 && qtd<0)
	//			return;
	//		pc.setQuantity(pc.getQuantity() + qtd);
	//	}
	public boolean remove(long itemId) {
		return itemList.removeIf(pc -> pc.getItem().getId() == itemId);
	}
	//private ItemCart addItem(long itemId) {
	//Product p = productDAO.findById(itemId);
	//ItemCart pc = new ItemCart();
	//pc.setItem(p);
	//	return pc;
	//}

}
