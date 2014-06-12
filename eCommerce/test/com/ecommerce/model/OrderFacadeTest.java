package com.ecommerce.model;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;

public class OrderFacadeTest {

	private MockOrderFacade orderFacade;
	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		this.orderFacade = new MockOrderFacade();
		this.em = this.orderFacade.getEm();
		
		TestUtils.populate();
	}

	@Test
	public void testShipOrder() {

		Long orderId = new Long(101);
		Order orderRetrived = this.em.find(Order.class, orderId);

		assertTrue("Order persisted", orderRetrived != null);
		assertTrue("Order not shipped yet", orderRetrived.getShipmentDate() == null);

		EntityTransaction txx = this.orderFacade.getEm().getTransaction();
		txx.begin();
		this.orderFacade.shipOrder(orderId);
		txx.commit();

		orderRetrived = this.em.find(Order.class, orderId);

		assertTrue("Order shipped", orderRetrived.getShipmentDate() != null);
	}
	
	@Test
	public void testGetCreator() {

		Long orderId = new Long(101);
		Order orderRetrived = this.em.find(Order.class, orderId);
		
		assertTrue("Order exists", orderRetrived != null);

		User expectedCreator = orderRetrived.getCreator();
		User realCreator = this.orderFacade.getCreator(orderId);

		assertTrue("Creator of 'getCreator' is correct", expectedCreator.equals(realCreator));
	}

}
