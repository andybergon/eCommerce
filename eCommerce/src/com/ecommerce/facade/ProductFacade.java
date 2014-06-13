package com.ecommerce.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecommerce.model.Product;

@Stateless
public class ProductFacade extends AbstractFacade<Product> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public ProductFacade() {
		super(Product.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}