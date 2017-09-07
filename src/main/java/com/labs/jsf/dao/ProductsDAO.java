package com.labs.jsf.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.labs.jsf.model.Products;

@ApplicationScoped
public class ProductsDAO {

	@PersistenceContext(unitName = "labs-persistence-unit")
	private EntityManager manager;

	public ProductsDAO() {

	}

	public ProductsDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public void save(Products product) {
		manager.persist(product);
	}

	public void remove(Products product) {
		manager.remove(product);
	}

	public List<Products> list() {
		return manager.createQuery("from Products b order by b.name desc", Products.class).getResultList();
	}

	public List<Products> search(String name) {
		TypedQuery<Products> query = manager.createQuery("from Products b where b.description like :searchKeyword or b.name like :searchKeyword order by b.name desc", Products.class);
		query.setParameter("searchKeyword", "%" + name + "%");
		return query.getResultList();
	}

	public Products findById(long id) {
		return manager.find(Products.class, id);
	}

}
