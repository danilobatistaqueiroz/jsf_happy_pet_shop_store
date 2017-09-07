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

import com.labs.jsf.dao.ProductsDAO;
import com.labs.jsf.model.Products;
import com.labs.jsf.util.MessagesHelper;

@RequestScoped
@Named
public class ProductsBean {

	private Products products = new Products();
	@Inject
	private ProductsDAO productsDAO;
	@Inject
	private MessagesHelper messagesHelper;

	@Transactional
	public String save() {

		productsDAO.save(products);

		messagesHelper.addFlash(new FacesMessage("Produto inserido com sucesso"));
		return "/admin/produtos/list?faces-redirect=true";
	}

	@Transactional
	public String update(Long id, Double price) {

		Products products = productsDAO.findById(id);
		products.setPrice(price);
		productsDAO.save(products);

		// FacesMessage message = new FacesMessage("Produto atualizado com
		// sucesso");
		// FacesContext.getCurrentInstance().addMessage("messages",message);
		reload();
		return "/admin/produtos/list?faces-redirect=true";
	}

	@Transactional
	public String remove(Long id) {

		Products products = productsDAO.findById(id);
		productsDAO.remove(products);
		reload();
		return "/admin/produtos/list?faces-redirect=true";
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
