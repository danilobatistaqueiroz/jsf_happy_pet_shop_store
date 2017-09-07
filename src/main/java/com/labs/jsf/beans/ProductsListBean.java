package com.labs.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.labs.jsf.dao.ProductsDAO;
import com.labs.jsf.model.Products;

@ManagedBean
@RequestScoped
public class ProductsListBean {

	private String productSearch;

	public String getProductSearch() {
		return productSearch;
	}

	public void setProductSearch(String productSearch) {
		this.productSearch = productSearch;
	}

	@Inject
	private ProductsDAO productsDAO;
	private List<Products> listProducts = new ArrayList<>();

	@PostConstruct
	private void loadObjects() {
		System.out.println("carregando os produtos");
		this.listProducts = productsDAO.list();
	}

	public void search(String name) {
		System.out.println("pesquisando os produtos");
		this.listProducts = productsDAO.search(name);
	}

	public List<Products> getListOfProducts() {
		System.out.println("carregando a lista de produtos");
		return listProducts;
	}

}
