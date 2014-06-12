package com.ecommerce.model;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;

public class OrderFacadeTest {

	private MockOrderFacade orderFacade;
	private EntityManager em;
	private EntityTransaction tx;

	private Long orderId;

	@Before
	public void setUp() throws Exception {
		this.orderFacade = new MockOrderFacade();
		this.em = this.orderFacade.getEm();
		this.tx = this.em.getTransaction();
	}

	@Test
	public void testShipOrder() {
		this.populate();

		Order orderRetrived = this.em.find(Order.class, this.orderId);

		assertTrue("Order persisted", orderRetrived != null);
		assertTrue("Order not shipped yet", orderRetrived.getShipmentDate() == null);

		EntityTransaction txx = this.orderFacade.getEm().getTransaction();
		txx.begin();
		this.orderFacade.shipOrder(this.orderId);
		txx.commit();

		orderRetrived = this.em.find(Order.class, this.orderId);

		assertTrue("Order shipped", orderRetrived.getShipmentDate() != null);
	}

	private void populate() {
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
		
		this.tx.begin();
		this.em.persist(creator);
		this.tx.commit();
		
		Order order = new Order();
		order.setCreationDate(new Date());
		order.setConfirmationDate(new Date());
		order.setCreator(creator);

		this.tx.begin();
		this.em.persist(order);
		this.tx.commit();

		this.orderId = order.getId();
	}

}
