package com.ecommerce.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.ProductFacade;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductRegister;
import com.ecommerce.utils.Utils;

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
		//TODO: add ProductRegister
		Product product = new Product(this.code, this.name , this.price, this.description, new ProductRegister());
		this.productFacade.create(product);
		this.product = product;
		return "product" + Utils.REDIRECT;
	}

	public String listProducts() {
		this.products = this.productFacade.findAll();
		return "products" + Utils.REDIRECT;
	}

	public String findProduct() {
		this.product = this.productFacade.find(this.id);
		return "product" + Utils.REDIRECT;
	}

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public String findProduct(Long id) {
		this.product = this.productFacade.find(id);
		return "product" + Utils.REDIRECT;
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
