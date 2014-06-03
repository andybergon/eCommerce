package com.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	private Float price;

	private String description;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "product")
	private ProductRegister register;

	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Provider> providers;

	public Product() {}

	public Product(String code, String name, Float price, String description, ProductRegister register) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.register = register;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductRegister getRegister() {
		return register;
	}

	public void setRegister(ProductRegister register) {
		this.register = register;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	@Override
	public boolean equals(Object obj) {
		Product product = (Product) obj;
		return this.getCode().equals(product.getCode());
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Product");
		sb.append("{id=").append(id);
		sb.append(", name='").append(name);
		sb.append(", price=").append(price);
		sb.append(", description='").append(description);
		sb.append(", code='").append(code);
		sb.append("}\n");
		return sb.toString();
	}

}
