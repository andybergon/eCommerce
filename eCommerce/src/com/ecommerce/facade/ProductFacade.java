package com.ecommerce.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecommerce.model.Product;

@Stateless
public class ProductFacade extends AbstractFacade<Product> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public ProductFacade() {
		super(Product.class);
	}

	public Product find(String productCode) {
		TypedQuery<Product> q = this.em.createQuery("SELECT p FROM Product p WHERE p.code = '" + productCode + "'",
				Product.class).setMaxResults(1);
		List<Product> result = q.getResultList();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}