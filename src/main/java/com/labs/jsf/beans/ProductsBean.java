package com.labs.jsf.beans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import com.labs.jsf.dao.ProductDAO;
import com.labs.jsf.model.Product;
import com.labs.jsf.util.MessagesHelper;

@RequestScoped
@Named
public class ProductsBean {

	private Product product = new Product();
	@Inject
	private ProductDAO productDAO;
	@Inject
	private MessagesHelper messagesHelper;

	@Transactional
	public String save() {

		productDAO.save(product);

		//FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Product Edition", "Product inserted with suscess!");
		//FacesContext context = FacesContext.getCurrentInstance();
		//context.addMessage("updateButton", message);
		return "/admin_startpage?faces-redirect=true";
	}

	@Transactional
	public String update() {
		System.out.println("updating product - price:"+product.getPrice());

		Product products = productDAO.findById(product.getId());
		products.setPrice(product.getPrice());
		productDAO.save(products);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Product Edition", "Product updated with suscess!");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("messages", message);
		reload();
		return "/admin_startpage?faces-redirect=true";
	}

	@Transactional
	public String remove() {

		Product products = productDAO.findById(product.getId());
		productDAO.remove(products);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Product Edition", "Product removed with suscess!");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("messages", message);
		reload();
		return "/admin_startpage?faces-redirect=true";
	}

	public void reload() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
