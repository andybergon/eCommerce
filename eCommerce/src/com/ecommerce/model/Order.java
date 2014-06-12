package com.ecommerce.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	private Date confirmationDate;

	@Temporal(TemporalType.DATE)
	private Date shipmentDate;

	@OneToOne(fetch = FetchType.EAGER)
	@Column(nullable = false)
	private User creator;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "orders_id")
	private List<OrderLine> orderLines;

	public Order() {}

	public Order(User creator) {
		this.creator = creator;
		this.creationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

}
