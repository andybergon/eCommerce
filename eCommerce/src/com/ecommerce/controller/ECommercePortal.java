package com.ecommerce.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ecommerce.model.SignedInState;

@ManagedBean(name = "portal")
@SessionScoped
public class ECommercePortal {
	private SignedInState signedInState;

	public ECommercePortal() {
		this.signedInState = SignedInState.NOT_SIGNED_IN;
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
}