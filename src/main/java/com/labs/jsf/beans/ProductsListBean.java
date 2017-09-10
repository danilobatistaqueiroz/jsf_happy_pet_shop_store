package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import com.labs.jsf.dao.ProductDAO;
import com.labs.jsf.model.Product;

@Named
@ViewScoped
public class ProductsListBean implements Serializable {

	private static final long serialVersionUID = -8474579766112727807L;

	private boolean edit;
	public boolean isEdit() {
		return edit;
	}

	private String productSearch;
	public String getProductSearch() {
		return productSearch;
	}
	public void setProductSearch(String productSearch) {
		this.productSearch = productSearch;
	}

	@Inject
	private ProductDAO productDAO;
	private List<Product> listProducts = new ArrayList<>();
	private Product product = new Product();
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	@PostConstruct
	public void init() {
		System.out.println("passa aqui !!!!");
		if(this.listProducts.size()==0){
			System.out.println("$$$$$$$ loading products");
			this.listProducts = productDAO.list();
		}
	}

	public void search(String name) {
		System.out.println("searching products");
		//this.listProducts = productDAO.search(name);
	}

	public List<Product> getListOfProducts() {
		System.out.println("getting the list of products");
		return listProducts;
	}

	public void edit(Product product) {
		System.out.println("### editable=true  ###");
		editMode(true, product);
	}

	@Transactional
	public void save(Product product) {
		System.out.println("saving the product: id:"+product.getId()+"price:"+product.getPrice());
		Product dbProduct = productDAO.findById(product.getId());
		dbProduct.setPrice(product.getPrice());
		productDAO.save(dbProduct);
		editMode(false, new Product());
	}

	@Transactional
	public void remove(Product product) {
		System.out.println("removing");
		Product dbProduct = productDAO.findById(product.getId());
		productDAO.remove(dbProduct);
	}

	private void editMode(boolean edit, Product product){
		this.product = product;
		this.edit = edit;
	}

}
