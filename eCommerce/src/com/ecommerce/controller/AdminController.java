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

	@ManagedProperty(value="#{portal}")
	private ECommercePortal portal;

	private Admin currentAdmin;

	private Credentials credentials;

	public AdminController() {
		this.credentials = new Credentials();
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
					this.portal.setMessage("You have successfuly signed in.");
					return "index" + Utils.REDIRECT;
				} else
					this.portal.setMessage("Incorrect password.");
			} else
				this.portal.setMessage("E-mail provided is not registered.");
		} else
			this.portal.setMessage("You must be signed out to perform this action.");

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
