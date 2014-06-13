package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.OrderFacade;
import com.ecommerce.model.ECommerce;
import com.ecommerce.model.Product;
import com.ecommerce.model.SignedInState;

@ManagedBean(name="portal")
@SessionScoped
public class ECommercePortal {
	@ManagedProperty(value = "#{eCommerce}")
	private ECommerce eCommerce;

	private String message;

	private Product currentProduct;

	private SignedInState signedInState;

	@EJB
	private OrderFacade orderFacade;

	public ECommercePortal() {
		this.signedInState = SignedInState.NOT_SIGNED_IN;
	}

	public void clean() {
		this.message = null;
	}

	public ECommerce geteCommerce() {
		return eCommerce;
	}

	public void seteCommerce(ECommerce eCommerce) {
		this.eCommerce = eCommerce;
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

	public boolean isSignedIn() {
		return this.signedInState != SignedInState.NOT_SIGNED_IN;
	}

	public SignedInState getSignedInState() {
		return signedInState;
	}

	public void setSignedInState(SignedInState signedInState) {
		this.signedInState = signedInState;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}