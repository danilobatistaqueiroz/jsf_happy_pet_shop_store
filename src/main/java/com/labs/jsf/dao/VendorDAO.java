package com.labs.jsf.dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.labs.jsf.model.Vendor;

@RequestScoped
public class VendorDAO {

	@Inject
	private EntityManager manager;

	public VendorDAO() {

	}

	public VendorDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public void save(Vendor vendor) {
		manager.persist(vendor);
	}

	public void remove(int id) {
		Vendor vendor = manager.getReference(Vendor.class, id);
		manager.remove(vendor);
	}

	public void remove(Vendor vendor) {
		manager.remove(vendor);
	}

	public List<Vendor> list() {
		List<Vendor> p = manager.createQuery("from Vendor b order by b.name desc", Vendor.class).getResultList();
		return p;
	}
	public List<String> listNames() {
		TypedQuery<String> query = manager.createQuery("Select UPPER(e.name) from Vendor e",String.class);
		List<String> p = query.getResultList();
		return p;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Vendor vendor) {
		manager.persist( vendor );
	}

	public List<Vendor> search(String name) {
		TypedQuery<Vendor> query = manager.createQuery("	from Vendor b where b.name like :searchKeyword order by b.name desc", Vendor.class);
		query.setParameter("searchKeyword", "%" + name + "%");
		return query.getResultList();
	}

	public Vendor findById(int id) {
		return manager.find(Vendor.class, id);
	}

}
