package com.ecommerce.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderFacade {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;
}