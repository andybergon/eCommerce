package com.ecommerce.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductFacade {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;
}