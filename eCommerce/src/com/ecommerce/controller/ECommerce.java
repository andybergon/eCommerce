package com.ecommerce.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ecommerce.model.ProductRegister;

@ManagedBean
@SessionScoped
public class ECommerce {
	private ProductRegister productRegister;

	public ProductRegister getProductRegister() {
		return productRegister;
	}

	public void setProductRegister(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}
}