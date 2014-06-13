package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.ProductFacade;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductRegister;
import com.ecommerce.model.Provider;
import com.ecommerce.model.SignedInState;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class ProductController {
	@EJB
	private ProductFacade productFacade;

	@ManagedProperty(value = "#{portal}")
	private ECommercePortal portal;

	private Product currentProduct;

	private Product newProduct;

	private List<Product> products;

	public ProductController() {
		ProductRegister productRegister = new ProductRegister();
		this.newProduct = new Product();

		this.newProduct.setRegister(productRegister);
		this.newProduct.setProviders(new ArrayList<Provider>());

		productRegister.setProduct(newProduct);
	}

	public String createProduct() {
		if (this.portal.getSignedInState().equals(SignedInState.ADMIN_SIGNED_IN)) {
			this.productFacade.create(newProduct);
			this.currentProduct = newProduct;
			//TODO: and object instance variables: this.newProduct = new Product();
			return "product" + Utils.REDIRECT;
		} else {
			this.portal.setMessage("You must be an admin to perform this action.");
			return "new_product" + Utils.REDIRECT;
		}
	}

	public String listProducts() {
		this.products = this.productFacade.findAll();
		return "products" + Utils.REDIRECT;
	}

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public ECommercePortal getPortal() {
		return portal;
	}

	public void setPortal(ECommercePortal portal) {
		this.portal = portal;
	}

	public String findProduct(Long id) {
		this.newProduct = this.productFacade.find(id);
		return "product" + Utils.REDIRECT;
	}

	public Product getNewProduct() {
		return this.newProduct;
	}

	public void setNewProduct(Product product) {
		this.newProduct = product;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}
}