package com.ecommerce.facade;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.ecommerce.utils.TestUtils;

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

		TypedQuery<Order> result = this.em.createQuery("SELECT o FROM Order o", Order.class);
		List<Order> orders = result.getResultList();
		Order orderRetrived = orders.get(0);

		assertTrue("Order persisted", orderRetrived != null);
		assertTrue("Order not shipped yet", orderRetrived.getShipmentDate() == null);

		EntityTransaction txx = this.orderFacade.getEm().getTransaction();
		txx.begin();
		this.orderFacade.shipOrder(orderRetrived.getId());
		txx.commit();

		orderRetrived = this.em.find(Order.class, orderRetrived.getId());

		assertTrue("Order shipped", orderRetrived.getShipmentDate() != null);
	}

	@Test
	public void testGetCreator() {

		TypedQuery<Order> result = this.em.createQuery("SELECT o FROM Order o", Order.class);
		List<Order> orders = result.getResultList();
		Order orderRetrived = orders.get(0);

		assertTrue("Order exists", orderRetrived != null);

		User expectedCreator = orderRetrived.getCreator();
		User realCreator = this.orderFacade.getCreator(orderRetrived.getId());

		assertTrue("Creator of 'getCreator' is correct", expectedCreator.equals(realCreator));
	}

}
