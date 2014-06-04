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

public class ProductTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;

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

	@Before
	public void initTransaction() {
		tx = em.getTransaction();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Test
	public void testCreateAndRemoveProduct() throws Exception {
		Product product = new Product();
		product.setName("Keyboard");
		product.setCode("KB01");
		product.setDescription("A great keyboard!");
		product.setPrice(new Float(10.5));

		tx.begin();
		em.persist(product);
		tx.commit();

		assertNotNull("ID generated correctly", product.getId());

		List<Product> products = em.createQuery("SELECT p FROM Product p").getResultList();
		assertEquals("Product persisted", 1, products.size());

		tx.begin();
		em.remove(product);
		tx.commit();

		products = em.createQuery("SELECT p FROM Product p").getResultList();
		assertEquals("Product removed", 0, products.size());
	}

}
