package com.labs.jsf.dao;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.labs.jsf.model.Product;

@RequestScoped
public class ProductDAO {

	@Inject
	private EntityManager manager;

	public ProductDAO() {

	}

	public ProductDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public void save(Product product) {
		manager.persist(product);
	}

	public void remove(Product product) {
		manager.remove(product);
	}

	public List<Product> list() {
		List<Product> p = manager.createQuery("from Product b order by b.name desc", Product.class).getResultList();
		return p;
	}

	public List<Product> search(String name) {
		TypedQuery<Product> query = manager.createQuery("from Product b where b.description like :searchKeyword or b.name like :searchKeyword order by b.name desc", Product.class);
		query.setParameter("searchKeyword", "%" + name + "%");
		return query.getResultList();
	}

	public Product findById(long id) {
		return manager.find(Product.class, id);
	}

}
