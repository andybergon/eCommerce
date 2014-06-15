package com.ecommerce.model;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ecommerce.utils.TestUtils;

public class UserTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;

	@Before
	public void setUp() throws Exception {
		TestUtils.populate();
		tx = em.getTransaction();
	}

	@BeforeClass
	public static void createEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("ecommerce-unit-test");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void closeEntityManager() throws SQLException {
		if (em != null)
			em.close();
		if (emf != null)
			emf.close();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Test
	public void testCreateAndRemoveUser() throws Exception {

		String query = "SELECT u FROM User u";
				
		List<User> users = em.createQuery(query).getResultList();
		int initialSize = users.size();

		Address address = new Address();
		address.setStreet("Via A 1");
		address.setCity("Rome");
		address.setState("Italy");
		address.setZipcode("00125");
		address.setCountry("Italy");
		
		User user = new User();
		user.setFirstName("A");
		user.setLastName("B");
		user.setEmail("a.b@domain.com");
		user.setPassword("password");
		user.setAddress(address);

		tx.begin();
		em.persist(user);
		tx.commit();

		assertNotNull("ID generated correctly", user.getId());

		users = em.createQuery(query).getResultList();
		assertEquals("User persisted", initialSize + 1, users.size());

		tx.begin();
		em.remove(user);
		tx.commit();

		users = em.createQuery(query).getResultList();
		assertEquals("User removed", initialSize, users.size());
	}

}
