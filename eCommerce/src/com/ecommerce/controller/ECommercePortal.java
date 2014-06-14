package com.ecommerce.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

import com.ecommerce.model.SignedInState;

@ManagedBean(name = "portal")
@SessionScoped
public class ECommercePortal {
	private String message;

	private SignedInState signedInState;

	public ECommercePortal() {
		this.signedInState = SignedInState.NOT_SIGNED_IN;
	}

	public void clean(PhaseEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("message");
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