package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.model.Admin;
import com.ecommerce.model.OrderFacade;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductFacade;
import com.ecommerce.model.User;
import com.ecommerce.model.UserFacade;

@ManagedBean
@SessionScoped
public class ECommercePortal {
	@ManagedProperty(value = "#{eCommerce}")
	private ECommerce eCommerce;

	private Admin currentAdmin;

	private User currentUser;

	private Product currentProduct;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private UserFacade userFacade;

	@EJB
	private OrderFacade orderFacade;

	public String findProduct(Long id) {
		this.currentProduct = this.productFacade.getProduct(id);
		return "product?faces-redirect=true";
	}

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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
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