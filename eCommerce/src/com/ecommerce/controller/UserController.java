package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.PhaseEvent;

import com.ecommerce.facade.OrderFacade;
import com.ecommerce.facade.UserFacade;
import com.ecommerce.model.Address;
import com.ecommerce.model.Credentials;
import com.ecommerce.model.Order;
import com.ecommerce.model.SignedInState;
import com.ecommerce.model.User;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class UserController {
	@EJB
	private UserFacade userFacade;

	@EJB
	private OrderFacade orderFacade;

	@ManagedProperty(value = "#{portal}")
	private ECommercePortal portal;

	private Credentials credentials;
	private User currentUser;
	private User newUser;
	private User orderCreator;
	private Long orderId;
	private String message;

	public UserController() {
	}

	public String createCredentials() {
		this.credentials = new Credentials();
		return "user_signin" + Utils.REDIRECT;
	}

	public String createNewUser() {
		this.newUser = new User();
		this.newUser.setAddress(new Address());
		this.newUser.setOrders(new ArrayList<Order>());
		this.credentials = new Credentials();
		return "user_signup" + Utils.REDIRECT;
	}

	public String signIn() {
		String password = credentials.getPassword();
		credentials.setPassword("");

		if (!this.portal.isSignedIn()) {
			User user = userFacade.findUser(credentials.getEmail());
			if (user != null) {
				if (user.checkPassword(password)) {
					this.portal.setSignedInState(SignedInState.USER_SIGNED_IN);
					this.currentUser = user;
					this.setMessage("You have successfuly signed in. Welcome back, " + user.getFirstName() + "!");
					return "index" + Utils.REDIRECT;
				} else
					this.setMessage("Incorrect password.");
			} else
				this.setMessage("E-mail provided is not registered.");
		} else
			this.setMessage("You must be signed out to perform this action.");

		return "user_signin" + Utils.REDIRECT;
	}

	public String signOut() {
		this.currentUser = null;
		this.portal.setSignedInState(SignedInState.NOT_SIGNED_IN);
		return "index" + Utils.REDIRECT;
	}

	public String signUp() {
		if (this.portal.isSignedIn())
			this.setMessage("You must be signed out to perform this action.");
		else {
			boolean userExists = this.userFacade.findUser(newUser.getEmail()) != null;
			if (userExists)
				this.setMessage("A user with this e-mail already exists.");
			else {
				this.newUser.setRegistrationDate(new Date());
				this.userFacade.create(newUser);
				this.credentials.setEmail(newUser.getEmail());
				this.credentials.setPassword(newUser.getPassword());
				return this.signIn();
			}
		}
		return "user_signup" + Utils.REDIRECT;
	}

	public boolean isSignedIn() {
		return this.portal.getSignedInState() == SignedInState.USER_SIGNED_IN;
	}

	public String findUserByOrder(User creator) {
		this.orderCreator = creator;
		return "order_creator" + Utils.REDIRECT;
	}

	public String findUserByOrder() {
		Order order = this.orderFacade.find(this.orderId);
		if (order != null) {
			this.orderId = null;
			this.orderCreator = order.getCreator();
			return "order_creator" + Utils.REDIRECT;
		}
		return "orders_all" + Utils.REDIRECT;
	}

	public void clean(PhaseEvent event) {
		this.message = null;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
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


	public ECommercePortal getPortal() {
		return portal;
	}

	public void setPortal(ECommercePortal portal) {
		this.portal = portal;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public User getOrderCreator() {
		return orderCreator;
	}

	public void setOrderCreator(User orderCreator) {
		this.orderCreator = orderCreator;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
