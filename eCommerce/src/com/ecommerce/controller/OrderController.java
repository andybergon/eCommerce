package com.ecommerce.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.OrderFacade;
import com.ecommerce.facade.ProductFacade;
import com.ecommerce.facade.ProductSupplyFacade;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderLine;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class OrderController {
	@EJB
	private OrderFacade orderFacade;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private ProductSupplyFacade productSupplyFacade;

	@ManagedProperty(value = "#{userController}")
	private UserController userController;

	private Long id;
	private Date creationDate;
	private Date confirmationDate;
	private Date shipmentDate;

	private Order order;
	private List<Order> orders;
	private String quantity;
	private User creator;

	public String createOrder() {
		this.order = new Order();
		User currentUser = this.userController.getCurrentUser();
		this.order.setCreator(currentUser);
		this.order.setCreationDate(new Date());
		this.quantity = "0";
		return "new_order" + Utils.REDIRECT;
	}

	public String findConfirmedOrders() {
		this.orders = this.orderFacade.findConfirmedOrders();
		return "orders_confirmed" + Utils.REDIRECT;
	}

	public String findOrder(Long orderId) {
		this.order = this.orderFacade.find(orderId);
		return "order" + Utils.REDIRECT;
	}

	public String findOrders(Long userId) {
		this.orders = this.orderFacade.findAllOrders(userId);
		return "my_orders" + Utils.REDIRECT;
	}

	public String findAllOrders() {
		this.orders = this.orderFacade.findAllOrders();
		return "orders_all" + Utils.REDIRECT;
	}

	public String addProductToOrder(Product product) {
		Integer quantity = Integer.parseInt(this.quantity);
		Float unitPrice = product.getPrice();
		boolean alreadyPresent = false;
		for (OrderLine orderLine : this.order.getOrderLines()) {
			if (orderLine.getProduct().getId().equals(product.getId())) {
				alreadyPresent = true;
				orderLine.addQuantity(quantity);
				break;
			}
		}
		if (!alreadyPresent) {
			OrderLine orderLine = new OrderLine(quantity, unitPrice, product);
			this.order.addOrderLine(orderLine);
		}
		this.quantity = "0";
		return "new_order" + Utils.REDIRECT;
	}

	public String updateQuantity(OrderLine orderLine) {
		if (orderLine.getQuantity() == 0)
			this.order.getOrderLines().remove(orderLine);
		return "new_order" + Utils.REDIRECT;
	}

	public String confirmOrder() {
		boolean allProductsHaveProvider = true;
		for (OrderLine ol : this.order.getOrderLines())
			if (!ol.getProduct().hasProvider()) {
				allProductsHaveProvider = false;
				break;
			}
		if (allProductsHaveProvider && !this.order.isEmpty()) {
			this.order.setConfirmationDate(new Date());
			this.orderFacade.create(this.order);
			return findOrders(this.userController.getCurrentUser().getId());
		} else {
			return "new_order" + Utils.REDIRECT;
		}
	}

	public String shipOrder(Order order) {
		for (OrderLine ol : order.getOrderLines()) {
			ol.shipOrderLine();
			this.productSupplyFacade.confirmSupply(ol.getConfirmedProvider(), ol.getProduct());
		}
		order.setShipmentDate(new Date());
		this.orderFacade.update(order);
		return findConfirmedOrders();
	}

	//getters & setters
	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}

	public Long getId() {
		return this.id;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public ProductSupplyFacade getProductSupplyFacade() {
		return productSupplyFacade;
	}

	public void setProductSupplyFacade(ProductSupplyFacade productSupplyFacade) {
		this.productSupplyFacade = productSupplyFacade;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}
