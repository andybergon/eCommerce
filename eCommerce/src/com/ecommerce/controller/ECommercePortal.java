package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.model.Admin;
import com.ecommerce.model.OrderFacade;
import com.ecommerce.model.User;

@ManagedBean
@SessionScoped
public class ECommercePortal {
	@ManagedProperty(value="#{eCommerce}")
	private ECommerce eCommerce;
	private Admin currentAdmin;
	private User currentUser;

	@EJB
	OrderFacade productFacade;

	@EJB
	OrderFacade orderFacade;

	public ECommerce getECommerce() {
		return eCommerce;
	}

	public Admin getCurrentAdmin() {
		return currentAdmin;
	}

	public User getCurrentUser() {
		return currentUser;
	}
}