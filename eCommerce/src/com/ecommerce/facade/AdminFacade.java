package com.ecommerce.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecommerce.model.Admin;

@Stateless
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
		TypedQuery<Admin> q = this.em.createQuery("SELECT a FROM Admin a WHERE a.email='" + email + "'", Admin.class)
				.setMaxResults(1);
		List<Admin> result = q.getResultList();
		if (!result.isEmpty())
			return q.getResultList().get(0);
		return null;
		/*
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		Root<Admin> root = cq.from(Admin.class);
		cq.where(cb.equal(root.get("email"), email));
		cq.select(cq.from(Admin.class));
		List<Admin> result = this.em.createQuery(cq).getResultList();
		if (!result.isEmpty())
			return result.get(0);
		return null;*/
	}
}
