package com.ecommerce.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		this.getEntityManager().persist(entity);
	}

	public void update(T entity) {
		this.getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		this.getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(Object id) {
		return getEntityManager().find(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		CriteriaQuery<T> cq = (CriteriaQuery<T>) this.getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(this.entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public int count() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(this.entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	protected abstract EntityManager getEntityManager();
}