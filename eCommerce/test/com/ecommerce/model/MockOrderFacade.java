package com.ecommerce.model;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.ecommerce.facade.OrderFacade;

public class MockOrderFacade extends OrderFacade {

	public MockOrderFacade() {
		this.setEm(Persistence.createEntityManagerFactory("ecommerce-unit-test").createEntityManager());
	}

	public EntityManager getEm() {
		return super.getEntityManager();
	}

	public void setEm(EntityManager em) {
		super.setEntityManager(em);
	}

}
