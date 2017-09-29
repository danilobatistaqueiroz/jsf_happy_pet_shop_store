package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.labs.jsf.dao.ProductDAO;
import com.labs.jsf.dao.VendorDAO;
import com.labs.jsf.model.Product;
import com.labs.jsf.model.Vendor;

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
	private VendorDAO vendorDAO;

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
		if(this.listProducts.size()==0){
			this.listProducts = productDAO.list();
		}
	}

	public void search(String name) {
		System.out.println("searching products");
		//this.listProducts = productDAO.search(name);
	}

	public List<Product> getListOfProducts() {
		return listProducts;
	}
	
	public List<Vendor> listVendors() {
		List<Vendor> listVendors = vendorDAO.list();
		return listVendors;
	}
	public List<String> listNames() {
		List<String> listVendorsNames = vendorDAO.listNames();
		return listVendorsNames;
	}
	@Transactional
	public void persistVendor() {
		Vendor vendor = new Vendor( ); 
		vendor.setId( 2 );
		vendor.setName( "dell" );
		vendorDAO.persist(vendor);
	}

	public void edit(Product product) {
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
		System.out.println("removing the product by reference ");
		//Product dbProduct = productDAO.findById(product.getId());
		productDAO.remove(product);
	}

	private void editMode(boolean edit, Product product){
		this.product = product;
		this.edit = edit;
	}
	
	public void fatal(){
		FacesContext context = FacesContext.getCurrentInstance();
       	context.addMessage(null, new FacesMessage("Successful",  "Your message: yes") );
	}

}
