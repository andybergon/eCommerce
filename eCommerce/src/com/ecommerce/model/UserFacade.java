package com.ecommerce.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class UserFacade {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public User createUser(String firstName, String lastName, String email, String password) {
		User user = new User(firstName, lastName, email, password, null, null, null);
		this.em.persist(user);
		return user;
	}

	public User getUser(Long id) {
		User user = this.em.find(User.class, id);
		return user;
	}

	public User findUser(String email) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get("email"), email));
		cq.select(cq.from(User.class));
		List<User> result = this.em.createQuery(cq).getResultList();
		if (!result.isEmpty())
			return result.get(0);
		return null;
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