package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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

	@ManagedProperty(value = "#{portal}")
	private ECommercePortal portal;

	private Credentials credentials;
	private User currentUser;
	private User newUser;
	private User orderCreator;

	public User getOrderCreator() {
		return orderCreator;
	}

	public void setOrderCreator(User orderCreator) {
		this.orderCreator = orderCreator;
	}

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
					this.portal
					.setMessage("You have successfuly signed in. Welcome back, " + user.getFirstName() + "!");
					return "index" + Utils.REDIRECT;
				} else
					this.portal.setMessage("Incorrect password.");
			} else
				this.portal.setMessage("E-mail provided is not registered.");
		} else
			this.portal.setMessage("You must be signed out to perform this action.");

		return "user_signin" + Utils.REDIRECT;
	}

	public String signOut() {
		this.currentUser = null;
		this.portal.setSignedInState(SignedInState.NOT_SIGNED_IN);
		return "index" + Utils.REDIRECT;
	}

	public String signUp() {
		if (this.portal.isSignedIn())
			this.portal.setMessage("You must be signed out to perform this action.");
		else {
			boolean userExists = this.userFacade.findUser(newUser.getEmail()) != null;
			if (userExists)
				this.portal.setMessage("A user with this e-mail already exists.");
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
}
