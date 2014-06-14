package com.ecommerce.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column
	private String phoneNumber;

	@Column
	private String email;

	@Column
	private String vatin;

	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
	private Map<Product, ProductSupply> inventories;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;

	public Provider() {
	}

	public Provider(String name, String phoneNumber, String email, String vatin, Address address) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
		this.setVatin(vatin);
		this.setAddress(address);
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	public Map<Product, ProductSupply> getInventories() {
		return inventories;
	}

	public void setInventories(Map<Product, ProductSupply> inventories) {
		this.inventories = inventories;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
