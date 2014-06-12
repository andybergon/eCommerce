package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Credentials;
import com.ecommerce.model.OrderFacade;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductFacade;
import com.ecommerce.model.SignedInState;
import com.ecommerce.model.User;
import com.ecommerce.model.UserFacade;

@ManagedBean
@SessionScoped
public class ECommercePortal {
	@ManagedProperty(value = "#{eCommerce}")
	private ECommerce eCommerce;

	private String message;

	private Enum<SignedInState> signedInState;

	private Admin currentAdmin;

	private User currentUser;

	private Product currentProduct;

	private Credentials credentials;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private UserFacade userFacade;

	@EJB
	private OrderFacade orderFacade;

	public ECommercePortal() {
		this.credentials = new Credentials();
		this.signedInState = SignedInState.NOT_SIGNED_IN;
	}

	public boolean isSignedIn() {
		return this.signedInState != SignedInState.NOT_SIGNED_IN;
	}

	public String signInUser() {
		String password = credentials.getPassword();
		credentials.setPassword("");

		if (this.signedInState == SignedInState.NOT_SIGNED_IN) {
			User user = userFacade.findUser(credentials.getEmail());
			if (user != null) {
				if (user.checkPassword(password)) {
					this.signedInState = SignedInState.USER_SIGNED_IN;
					this.currentUser = user;
					this.message = "You have successfuly signed in.<br/>Welcome back, " + user.getFirstName() + "!";
					return "index?faces-redirect=true";
				} else
					this.message = "Incorrect password.";
			} else
				this.message = "Email provided is not registered.";
		} else
			this.message = "You must be signed out to perform this action.";

		return "user_signin?faces-redirect=true";
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

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Enum<SignedInState> getSignedInState() {
		return signedInState;
	}

	public void setSignedInState(Enum<SignedInState> signedInState) {
		this.signedInState = signedInState;
	}
}