package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.OrderFacade;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;

@ManagedBean
@SessionScoped
public class ECommercePortal {
	@ManagedProperty(value = "#{eCommerce}")
	private ECommerce eCommerce;

	private Admin currentAdmin;

	private Product currentProduct;

	@EJB
	private OrderFacade orderFacade;

	public ECommerce geteCommerce() {
		return eCommerce;
	}

	public void seteCommerce(ECommerce eCommerce) {
		this.eCommerce = eCommerce;
	}

	public Admin getCurrentAdmin() {
		return currentAdmin;
	}

	public void setCurrentAdmin(Admin currentAdmin) {
		this.currentAdmin = currentAdmin;
	}

	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}

	public Product getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}
}