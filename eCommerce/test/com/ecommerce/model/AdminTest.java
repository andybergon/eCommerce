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

public class AdminTest {

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
	public void testCreateAndRemoveAdmin() throws Exception {

		String query = "SELECT a FROM Admin a";
		
		List<Admin> admins = em.createQuery(query).getResultList();
		int initialSize = admins.size();

		Admin admin = new Admin();
		admin.setEmail("admin@domain.com");
		admin.setPassword("password");

		tx.begin();
		em.persist(admin);
		tx.commit();

		assertNotNull("ID generated correctly", admin.getId());

		admins = em.createQuery(query).getResultList();
		assertEquals("Admin persisted", initialSize + 1, admins.size());

		tx.begin();
		em.remove(admin);
		tx.commit();

		admins = em.createQuery(query).getResultList();
		assertEquals("Admin removed", initialSize, admins.size());
	}

}
