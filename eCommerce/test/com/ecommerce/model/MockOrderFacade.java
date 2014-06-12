package com.ecommerce.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.ecommerce.facade.OrderFacade;

public class MockOrderFacade extends OrderFacade {

	private EntityManager em;

	public MockOrderFacade() {
		this.em = Persistence.createEntityManagerFactory("ecommerce-unit-test").createEntityManager();
	}

	@Override
	public void shipOrder(Long orderId) {
		Order order = this.em.find(Order.class, orderId);
		order.setShipmentDate(new Date());
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
