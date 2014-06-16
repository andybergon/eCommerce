package com.ecommerce.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.PhaseEvent;

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

	@ManagedProperty(value="#{portal}")
	private ECommercePortal portal;

	private Admin currentAdmin;
	private Credentials credentials;
	private String message;

	public AdminController() {
	}

	public String createCredentials() {
		this.credentials = new Credentials();
		return "admin_signin" + Utils.REDIRECT;
	}

	public String signIn() {
		String password = credentials.getPassword();
		credentials.setPassword("");

		if (!portal.isSignedIn()) {
			Admin admin = adminFacade.findAdmin(this.credentials.getEmail());
			if (admin != null) {
				if (admin.checkPassword(password)) {
					this.portal.setSignedInState(SignedInState.ADMIN_SIGNED_IN);
					this.currentAdmin = admin;
					this.setMessage("You have successfuly signed in.");
					return "index" + Utils.REDIRECT;
				} else
					this.setMessage("Incorrect password.");
			} else
				this.setMessage("E-mail provided is not registered.");
		} else
			this.setMessage("You must be signed out to perform this action.");

		return "admin_signin" + Utils.REDIRECT;
	}

	public String signOut() {
		this.currentAdmin = null;
		this.portal.setSignedInState(SignedInState.NOT_SIGNED_IN);
		return "index" + Utils.REDIRECT;
	}

	public boolean isSignedIn() {
		return this.portal.getSignedInState() == SignedInState.ADMIN_SIGNED_IN;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AdminFacade getAdminFacade() {
		return adminFacade;
	}

	public void setAdminFacade(AdminFacade adminFacade) {
		this.adminFacade = adminFacade;
	}

	public ECommercePortal getPortal() {
		return portal;
	}

	public void setPortal(ECommercePortal portal) {
		this.portal = portal;
	}

	public Admin getCurrentAdmin() {
		return currentAdmin;
	}

	public void setCurrentAdmin(Admin currentAdmin) {
		this.currentAdmin = currentAdmin;
	}
}
