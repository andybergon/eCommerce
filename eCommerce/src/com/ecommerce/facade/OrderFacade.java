package com.ecommerce.facade;

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

import com.ecommerce.model.Order;
import com.ecommerce.model.User;

@Stateless
public class OrderFacade extends AbstractFacade<Order> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public OrderFacade() {
		super(Order.class);
	}

	public User getCreator(Long orderId) {
		Order order = this.em.find(Order.class, orderId);
		User creator = order.getCreator(); //TODO: works??
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

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}