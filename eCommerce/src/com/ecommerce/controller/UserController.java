package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.UserFacade;
import com.ecommerce.model.Credentials;
import com.ecommerce.model.SignedInState;
import com.ecommerce.model.User;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class UserController {
	@EJB
	private UserFacade userFacade;

	private User currentUser;

	private Credentials credentials;

	private String message;

	private Enum<SignedInState> signedInState;

	public UserController() {
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
					return "index" + Utils.REDIRECT;
				} else
					this.message = "Incorrect password.";
			} else
				this.message = "Email provided is not registered.";
		} else
			this.message = "You must be signed out to perform this action.";

		return "user_signin" + Utils.REDIRECT;
	}

	public Enum<SignedInState> getSignedInState() {
		return signedInState;
	}

	public void setSignedInState(Enum<SignedInState> signedInState) {
		this.signedInState = signedInState;
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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
