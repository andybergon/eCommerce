package com.ecommerce.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductSupply;
import com.ecommerce.model.Provider;

@Stateless
public class ProductSupplyFacade extends AbstractFacade<ProductSupply> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public ProductSupplyFacade() {
		super(ProductSupply.class);
	}

	public void confirmSupply(Provider provider, Product product) {
		for (ProductSupply ps : provider.getInventories())
			if (ps.getProduct().equals(product)) {
				this.update(ps);
				break;
			}
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
