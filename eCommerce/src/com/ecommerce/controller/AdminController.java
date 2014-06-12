package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.AdminFacade;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Credentials;
import com.ecommerce.model.SignedInState;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class AdminController {
	@EJB
	private AdminFacade adminFacade;

	@ManagedProperty(value="#{eCommercePortal}")
	private ECommercePortal eCommercePortal;

	private Admin currentAdmin;

	private Credentials credentials;

	private String message;


	public AdminController() {
		this.credentials = new Credentials();
	}

	public String signInAdmin() {
		String password = credentials.getPassword();
		credentials.setPassword("");

		if (!eCommercePortal.isSignedIn()) {
			Admin admin = adminFacade.findAdmin(credentials.getEmail());
			if (admin != null) {
				if (admin.checkPassword(password)) {
					this.eCommercePortal.setSignedInState(SignedInState.ADMIN_SIGNED_IN);
					this.currentAdmin = admin;
					this.message = "You have successfuly signed in.";
					return "index" + Utils.REDIRECT;
				} else
					this.message = "Incorrect password.";
			} else
				this.message = "Email provided is not registered.";
		} else
			this.message = "You must be signed out to perform this action.";

		return "admin_signin" + Utils.REDIRECT;
	}

	public void clean() {
		this.message = null;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public AdminFacade getAdminFacade() {
		return adminFacade;
	}

	public void setAdminFacade(AdminFacade adminFacade) {
		this.adminFacade = adminFacade;
	}

	public Admin getCurrentAdmin() {
		return currentAdmin;
	}

	public void setCurrentadmin(Admin currentAdmin) {
		this.currentAdmin = currentAdmin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
