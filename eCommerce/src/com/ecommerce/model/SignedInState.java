package com.ecommerce.model;

public enum SignedInState {
	NOT_SIGNED_IN, USER_SIGNED_IN, ADMIN_SIGNED_IN;

	public boolean isSignedIn() {
		return this != NOT_SIGNED_IN;
	}

	public boolean isUserSignedIn() {
		return this == USER_SIGNED_IN;
	}

	public boolean isAdminSignedIn() {
		return this == ADMIN_SIGNED_IN;
	}
}