package com.ecommerce.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductSupply;
import com.ecommerce.model.Provider;

@Stateless
public class ProviderFacade extends AbstractFacade<Provider> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public ProviderFacade() {
		super(Provider.class);
	}

	@SuppressWarnings("unchecked")
	public Map<Product, ProductSupply> findAllProducts(Long id) {
		List<Object[]> results = this.em.createQuery(
				"SELECT prod, ps "
						+ "FROM Provider prov, ProductSupply ps, Product prod "
						+ "WHERE prov.id = ps.id AND ps.id=prod.id AND prov.id=" + id).getResultList();

		Map<Product, ProductSupply> products = new HashMap<Product, ProductSupply>();

		for(Object[] result : results)
			products.put((Product) result[0], (ProductSupply) result[1]);

		return products;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
