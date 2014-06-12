package com.ecommerce.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestUtils {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	public static void init() {
		emf = Persistence.createEntityManagerFactory("ecommerce-unit-test");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	public static void populate() {
		init();
		Address address = new Address();
		address.setStreet("Via Giuseppe Todeschini 43");
		address.setCity("Rome");
		address.setState("Italy");
		address.setZipcode("00125");
		address.setCountry("Italy");
		User creator = new User();
		creator.setFirstName("Andrea");
		creator.setLastName("Bergonzo");
		creator.setEmail("andybergon@libero.it");
		creator.setPassword("password");
		creator.setAddress(address);

		tx.begin();
		em.persist(creator);
		tx.commit();

		Order order = new Order();
		order.setCreationDate(new Date());
		order.setConfirmationDate(new Date());
		order.setCreator(creator);

		tx.begin();
		em.persist(order);
		tx.commit();

	}
	
}
