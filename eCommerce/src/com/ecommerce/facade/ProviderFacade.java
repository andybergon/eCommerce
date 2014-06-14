package com.ecommerce.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public List<ProductSupply> findInventories(Long id) {
		List<Object[]> results = this.em.createQuery(
				"SELECT prod.code, ps "
						+ "FROM Provider prov, ProductSupply ps, Product prod "
						+ "WHERE prov.id = ps.id AND ps.id=prod.id AND prov.id=" + id).getResultList();

		List<ProductSupply> inventories = new ArrayList<ProductSupply>();

		for(Object[] result : results)
			;//TODO: inventories.add((String) result[0], (ProductSupply) result[1]);

		return inventories;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
