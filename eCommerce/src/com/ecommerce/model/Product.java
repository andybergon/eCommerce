package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic
	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column
	private Float price;

	@Column
	private String description;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<ProductSupply> supplies;


	public Product() {
		this.supplies = new ArrayList<ProductSupply>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ProductSupply> getSupplies() {
		return supplies;
	}

	public void setSupplies(List<ProductSupply> supplies) {
		this.supplies = supplies;
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
