package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Integer quantity;

	private Float unitPrice;

	@OneToOne
	@Column(nullable = false)
	private Product product;

	@OneToOne
	private Provider confirmedProvider;

	public OrderLine() {
	}

	public OrderLine(Integer quantity, Float unitPrice, Product product) {
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.product = product;
	}

	public boolean isSupplied() {
		for (ProductSupply ps : this.product.getSupplies())
			if (ps.getQuantity() >= this.quantity)
				return true;
		return false;
	}

	public float getSubtotal() {
		return this.quantity * this.product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPrice() {
		return this.getUnitPrice() * this.getQuantity();
	}

	public void addQuantity(Integer quantity) {
		this.quantity += quantity;
	}

	public void removeQuantity(Integer quantity) {
		this.quantity -= quantity;
	}

	public void shipOrderLine() {
		List<ProductSupply> supplyCandidates = new ArrayList<ProductSupply>();

		for (ProductSupply ps : this.product.getSupplies()) {
			if (ps.getQuantity() >= this.quantity)
				supplyCandidates.add(ps);
		}

		int n = (int) (Math.random() * supplyCandidates.size());

		ProductSupply randomSupply = supplyCandidates.get(n);

		randomSupply.decrementQuantity(this.quantity);
		this.confirmedProvider = randomSupply.getProvider();
	}

	// getters & setters
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Provider getConfirmedProvider() {
		return confirmedProvider;
	}

	public void setConfirmedProvider(Provider confirmedProvider) {
		this.confirmedProvider = confirmedProvider;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", product=" + product
				+ "]";
	}
}