package br.com.db1.dao.impl;

import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.com.db1.dao.impl.AbstractTestCase;
import br.com.db1.model.Lembrete;

public class LembreteDaoTest extends AbstractTestCase {

	private LembreteDao dao;

	@Before
	public void init() {
		dao = new LembreteDao(manager);
	}

	@Test
	public void findAllTest() {
		Query queryLembrete = manager.createNativeQuery("DELETE FROM Lembrete");
		queryLembrete.executeUpdate();

		Assert.assertTrue(dao.findAll().size() == 0);

		Lembrete lembrete = new Lembrete();
		lembrete.setDescricao("OlaTeste");
		manager.persist(lembrete);

		Assert.assertTrue(dao.findAll().size() > 0);

	}

	@Test
	public void findByIdTest() {
		Lembrete lembrete = dao.findById(1);
		Assert.assertEquals("OlaTeste", lembrete.getDescricao());
		Assert.assertNotEquals("TesteErrado", lembrete.getDescricao());
	}

	@Test
	public void findByNameTest() {
		Query queryLembrete = manager.createNativeQuery("DELETE FROM Lembrete where descricao = :pDescricao");
		queryLembrete.setParameter("pDescricao", "OlaTeste");
		queryLembrete.executeUpdate();
		Assert.assertTrue(dao.findByName("OlaTeste").size() == 0);

		Lembrete lembrete = new Lembrete();
		lembrete.setDescricao("OlaSetDescricao");
		manager.persist(lembrete);

		Assert.assertTrue(dao.findByName("OlaSetDescricao").size() == 1);
	}

	@Test
	public void saveTest() {
		Query queryLembrete = manager.createNativeQuery("DELETE FROM Lembrete");
		queryLembrete.executeUpdate();
		Assert.assertTrue(dao.findAll().size() == 0);

		Lembrete lembrete = new Lembrete();
		lembrete.setDescricao("OlaTesteSave");
		dao.save(lembrete);

		Assert.assertTrue(dao.findAll().size() == 1);
		
		lembrete.setDescricao("OlaDescricao");
		dao.save(lembrete);
		Assert.assertTrue(dao.findAll().size() == 1);


	}

	@Test
	public void deleteTest() {
		Query queryLembrete = manager.createNativeQuery("DELETE FROM Lembrete");
		queryLembrete.executeUpdate();
		Assert.assertTrue(dao.findAll().size() == 0);

		Lembrete lembrete = new Lembrete();
		lembrete.setDescricao("PP");
		dao.save(lembrete);

		Assert.assertTrue(dao.findAll().size() == 1);
		
		dao.delete(lembrete.getId());
		Assert.assertTrue(dao.findAll().size() == 0);

	}

}