package com.ecommerce.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecommerce.model.Product;
import com.ecommerce.model.Provider;

@Stateless
public class ProviderFacade extends AbstractFacade<Provider> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public ProviderFacade() {
		super(Provider.class);
	}

	public List<Product> findAllProducts(Long id) {
		TypedQuery<Product> q = this.em.createQuery("SELECT p.products FROM Provider p WHERE p.id = " + id, Product.class);
		return q.getResultList();
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
