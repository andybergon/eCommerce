package com.ecommerce.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecommerce.model.User;

@Stateless
public class UserFacade extends AbstractFacade<User> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public UserFacade() {
		super(User.class);
	}

	public User findUser(String email) {
		TypedQuery<User> q = this.em.createQuery("SELECT u FROM User u WHERE u.email='" + email + "'", User.class)
				.setMaxResults(1);
		List<User> result = q.getResultList();
		if (!result.isEmpty())
			return q.getResultList().get(0);
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}