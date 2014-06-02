package com.ecommerce.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;

import com.ecommerce.model.Admin;
import com.ecommerce.model.ProductRegister;
import com.ecommerce.model.User;

@ManagedBean(eager=true)
@ApplicationScoped
public class ECommerce {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	@OneToMany
	@MapKey(name = "code")
	private Map<Integer, ProductRegister> productRegisters;

	@OneToMany
	private List<Admin> admins;

	@OneToMany
	private List<User> users;

	public ECommerce() {}

	public Map<Integer, ProductRegister> getProductRegisters() {
		return productRegisters;
	}

	public void setProductRegisters(Map<Integer, ProductRegister> productRegisters) {
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