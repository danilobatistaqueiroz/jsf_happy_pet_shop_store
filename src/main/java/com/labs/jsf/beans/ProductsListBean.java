package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import com.labs.jsf.dao.ProductDAO;
import com.labs.jsf.model.Product;

@ManagedBean
@SessionScoped
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

	public void init() {
		System.out.println("$$$$$$$ carregando os produtos");
		this.listProducts = productDAO.list();
	}

	public void search(String name) {
		System.out.println("pesquisando os produtos");
		this.listProducts = productDAO.search(name);
	}

	public List<Product> getListOfProducts() {
		System.out.println("carregando a lista de produtos");
		return listProducts;
	}

	public void edit(Product product) {
		System.out.println("### editable=true  ###");
		editMode(true, product);
	}

	@Transactional
	public void save(Product product) {
		System.out.println("saving the product: id:"+product.getId()+"price:"+product.getPrice());
		productDAO.save(product);
		editMode(false, new Product());
	}

	private void editMode(boolean edit, Product product){
		this.product = product;
		this.edit = edit;
	}

}
