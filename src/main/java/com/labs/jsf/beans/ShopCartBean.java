package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import com.labs.jsf.dao.ProductDAO;
import com.labs.jsf.model.Product;
import com.labs.jsf.model.ProductCart;

@SessionScoped
@ManagedBean
public class ShopCartBean implements Serializable {

	private static final long serialVersionUID = 4730813081157767208L;

	@Inject
	private ProductDAO productDAO;

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

	public void update(long productId) {
		update(productId, 1);
	}
	public void update(long productId, int qtd) {
		ProductCart pc = productsCart.stream().
				filter(p -> p.getProduct().getId() == productId).
				findFirst().orElse(addProductCart(productId));
		if(pc.getQuantity()==0 && qtd<0)
			return;
		pc.setQuantity(pc.getQuantity() + qtd);
	}
	public boolean remove(long productId) {
		return productsCart.removeIf(pc -> pc.getProduct().getId() == productId);
	}
	private ProductCart addProductCart(long productId) {
		Product p = productDAO.findById(productId);
		ProductCart pc = new ProductCart();
		pc.setProduct(p);
		return pc;
	}

}
