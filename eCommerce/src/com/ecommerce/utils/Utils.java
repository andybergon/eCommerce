package com.ecommerce.utils;

public class Utils {
	public static final String REDIRECT = "?faces-redirect=true";

	public static boolean isNumeric(String input) {
		return input.matches("[0-9]+");
	}


	public static float round(float value, int decimalPrecision) {
		float n = (float) Math.pow(10, decimalPrecision);
		return Math.round(value * n) / n;
	}
}