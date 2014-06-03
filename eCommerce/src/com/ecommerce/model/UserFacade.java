package com.ecommerce.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class UserFacade {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public User createUser(String firstName, String lastName, String email, String password) {
		User user = new User(firstName, lastName, email, password, null, null, null);
		this.em.persist(user);
		return user;
	}

	public User getUser(String email) {
		User user = this.em.find(User.class, email);
		return user;
	}

	public List<User> getAllUsers() {
		CriteriaQuery<User> cq = this.em.getCriteriaBuilder().createQuery(User.class);
		cq.select(cq.from(User.class));
		List<User> users = this.em.createQuery(cq).getResultList();
		return users;
	}
	//
	// public void updateProduct(Product product) {
	// this.em.merge(product);
	// }
	//
	// private void deleteProduct(Product product) {
	// this.em.remove(product);
	// }
	//
	// public void deleteProduct(Long id) {
	// Product product = this.em.find(Product.class, id);
	// this.deleteProduct(product);
	// }

}