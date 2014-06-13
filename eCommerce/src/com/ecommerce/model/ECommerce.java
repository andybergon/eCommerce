package com.ecommerce.model;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;

@ManagedBean(eager = true)
@ApplicationScoped
public class ECommerce {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	@OneToMany
	@MapKey(name = "product")
	private Map<Product, ProductRegister> productRegisters;

	@OneToMany
	private List<Admin> admins;

	@OneToMany
	private List<User> users;

	public ECommerce() {
	}

	public Map<Product, ProductRegister> getProductRegisters() {
		return productRegisters;
	}

	public void setProductRegisters(Map<Product, ProductRegister> productRegisters) {
		this.productRegisters = productRegisters;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}