package com.ecommerce.model;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class OrderFacade {

	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public Order getOrder(Long orderId) {
		Order order = this.em.find(Order.class, orderId);
		return order;
	}

	public User getCreator(Long orderId) {
		Order order = this.em.find(Order.class, orderId);
		User creator = order.getCreator(); // works??
		return creator;
	}

	public List<Order> getConfirmedOrders() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> o = cq.from(Order.class);
		Predicate condition = cb.isNotNull(o.get("confirmationdate"));
		cq.where(condition);
		TypedQuery<Order> q = this.em.createQuery(cq);
		List<Order> confirmedOrders = q.getResultList();
		return confirmedOrders;
	}

	public void shipOrder(Long orderId) {
		Order order = this.em.find(Order.class, orderId);
		order.setShipmentDate(new Date());
	}

}
