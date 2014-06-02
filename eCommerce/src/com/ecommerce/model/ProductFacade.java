package com.ecommerce.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class ProductFacade {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public Product createProduct(String name, String code, Float price, String description) {
		Product product = new Product(code, name, price, description, new ProductRegister());
		this.em.persist(product);
		return product;
	}

	public Product getProduct(Long id) {
		Product product = this.em.find(Product.class, id);
		return product;
	}

	public List<Product> getAllProducts() {
		CriteriaQuery<Product> cq = this.em.getCriteriaBuilder().createQuery(Product.class);
		cq.select(cq.from(Product.class));
		List<Product> products = this.em.createQuery(cq).getResultList();
		return products;
	}

	public void updateProduct(Product product) {
		this.em.merge(product);
	}

	private void deleteProduct(Product product) {
		this.em.remove(product);
	}

	public void deleteProduct(Long id) {
		Product product = this.em.find(Product.class, id);
		this.deleteProduct(product);
	}

}