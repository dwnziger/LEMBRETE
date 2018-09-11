package br.com.db1.model;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

public class LembreteTest extends AbstractTest {
	@Test
	public void inserirTest() {
		Query q = manager.createQuery("from lembrete where descricao = :pDescricao");
		q.setParameter("pDescricao", "PR");
		q.setMaxResults(1);
		Lembrete lembrete = (Lembrete) q.getSingleResult();

		manager.getTransaction().begin();
		manager.persist(lembrete);
		manager.getTransaction().commit();
	}

	@Test
	public void selectTest() {
		Query q = manager.createQuery("from lembrete");
		List<Lembrete> lembretes = q.getResultList();

		for (Lembrete lembrete : lembretes) {
			System.out.println(lembrete);
		}

	}

}