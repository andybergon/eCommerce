package com.ecommerce.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ecommerce.model.Address;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderLine;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

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
		
		Admin admin1 = new Admin();
		admin1.setEmail("admin1@domain.com");
		admin1.setPassword("password");
		
		Admin admin2 = new Admin();
		admin2.setEmail("admin2@domain.com");
		admin2.setPassword("password");
		
		tx.begin();
		em.persist(admin1);
		em.persist(admin2);
		tx.commit();
		
		Address address1 = new Address();
		address1.setStreet("Via Bianchi 1");
		address1.setCity("Rome");
		address1.setState("Italy");
		address1.setZipcode("00125");
		address1.setCountry("Italy");
		
		Address address2 = new Address();
		address2.setStreet("Via Rossi 1");
		address2.setCity("Rome");
		address2.setState("Italy");
		address2.setZipcode("00125");
		address2.setCountry("Italy");		
		
		Address address3 = new Address();
		address3.setStreet("Via Verdi 1");
		address3.setCity("Rome");
		address3.setState("Italy");
		address3.setZipcode("00125");
		address3.setCountry("Italy");
		
		User user1 = new User();
		user1.setFirstName("Andrea");
		user1.setLastName("Bergonzo");
		user1.setEmail("andrea.bergonzo@domain.com");
		user1.setPassword("password");
		user1.setAddress(address1);
		
		User user2 = new User();
		user2.setFirstName("David");
		user2.setLastName("Hessler");
		user2.setEmail("david.hessler@domain.com");
		user2.setPassword("password");
		user2.setAddress(address2);
		
		User user3 = new User();
		user3.setFirstName("BalusC");
		user3.setLastName("BalusC");
		user3.setEmail("balusc@domain.com");
		user3.setPassword("password");
		user3.setAddress(address3);
		
		tx.begin();
		// address are in cascade persist
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		tx.commit();

		Product product1 = new Product();
		product1.setCode("kb01");
		product1.setName("Keyboard A");
		product1.setPrice(new Float(25.5));
		product1.setDescription("A mechanichal keyboard!");
		
		Product product2 = new Product();
		product2.setCode("kb02");
		product2.setName("Keyboard B");
		product2.setPrice(new Float(10.0));
		product2.setDescription("A normal keyboard!");
		
		Product product3 = new Product();
		product3.setCode("ms01");
		product3.setName("Mouse A");
		product3.setPrice(new Float(8.0));
		product3.setDescription("A gaming mouse!");
		
		tx.begin();
		em.persist(product1);
		em.persist(product2);
		em.persist(product3);
		tx.commit();
		
		OrderLine orderLine1 = new OrderLine();
		orderLine1.setQuantity(new Integer(2));
		orderLine1.setProduct(product1);
		orderLine1.setUnitPrice(product1.getPrice());
		
		OrderLine orderLine2 = new OrderLine();
		orderLine2.setQuantity(new Integer(4));
		orderLine2.setProduct(product2);
		orderLine2.setUnitPrice(product2.getPrice());
		
		OrderLine orderLine3 = new OrderLine();
		orderLine3.setQuantity(new Integer(3));
		orderLine3.setProduct(product3);
		orderLine3.setUnitPrice(product3.getPrice());
		
		Order order1 = new Order();
		order1.setCreationDate(new Date());
		order1.setConfirmationDate(new Date());
		order1.setCreator(user1);
		List<OrderLine> orderLines1 = new ArrayList<OrderLine>();
		orderLines1.add(orderLine1);
		orderLines1.add(orderLine2);
		order1.setOrderLines(orderLines1);

		Order order2 = new Order();
		order2.setCreationDate(new Date());
		order2.setConfirmationDate(new Date());
		order2.setCreator(user2);
		List<OrderLine> orderLines2 = new ArrayList<OrderLine>();
		orderLines2.add(orderLine3);
		order2.setOrderLines(orderLines2);
		
		tx.begin();
		// orderlines are in cascade persist
		em.persist(order1);
		em.persist(order2);
		tx.commit();

	}
	
}
