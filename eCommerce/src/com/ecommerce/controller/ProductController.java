package com.ecommerce.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductFacade;

@ManagedBean
@SessionScoped
public class ProductController {

	// @ManagedProperty(value = "#{param.id}")
	private Long id;
	private String code;
	private String name;
	private Float price;
	private String description;
	private Product product;
	private List<Product> products;

	@EJB
	private ProductFacade productFacade;

	public String createProduct() {
		this.product = this.productFacade.createProduct(this.code, this.name , this.price, this.description);
		// return "product";
		return "product" + "?faces-redirect=true";
	}

	public String listProducts() {
		this.products = this.productFacade.getAllProducts();
		// return "products";
		return "products" + "?faces-redirect=true";
	}

	public String findProduct() {
		this.product = this.productFacade.getProduct(this.id);
		// return "product";
		return "product" + "?faces-redirect=true";
	}

	public String findProduct(Long id) {
		this.product = this.productFacade.getProduct(id);
		// return "product";
		return "product" + "?faces-redirect=true";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
