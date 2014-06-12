package com.ecommerce.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ecommerce.model.User;

@Stateless
public class UserFacade extends AbstractFacade<User> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public UserFacade(Class<User> entityClass) {
		super(entityClass);
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

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}