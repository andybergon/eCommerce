package com.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@Column(nullable = false)
	private User creator;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "orders_id")
	private List<OrderLine> orderLines;

	public Order() {
		this.orderLines = new ArrayList<OrderLine>();
	}

	public Order(User creator) {
		this.creator = creator;
		this.creationDate = new Date();
		this.orderLines = new ArrayList<OrderLine>();
	}
	
	public void addOrderLine(OrderLine orderLine) {
		this.orderLines.add(orderLine);
	}

	public void removeOrderLine(OrderLine orderLine) {
		for (Iterator<OrderLine> iterator = this.orderLines.listIterator(); iterator.hasNext();) {
			OrderLine currentOrderLine = iterator.next();
			if (currentOrderLine.equals(orderLine)) {
				iterator.remove();
			}
		}
	}

	public int countOrderLines() {
		return this.orderLines.size();
	}

	public int countProducts() {
		int count = 0;
		for (OrderLine orderLine : this.orderLines) {
			count += orderLine.getQuantity();
		}
		return count;
	}

	public float getTotal() {
		float total = 0;
		for (OrderLine orderLine : this.orderLines) {
			total += orderLine.getPrice();
		}
		return total;
	}

	public boolean isEmpty() {
		return this.orderLines.isEmpty();
	}

	
	//getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

}
