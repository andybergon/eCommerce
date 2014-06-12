package com.ecommerce.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;

public class AdminFacade extends AbstractFacade<Admin> {
	@PersistenceContext(unitName = "ecommerce-unit")
	private EntityManager em;

	public AdminFacade() {
		super(Admin.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public Admin findAdmin(String email) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get("email"), email));
		cq.select(cq.from(Admin.class));
		List<Admin> result = this.em.createQuery(cq).getResultList();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
