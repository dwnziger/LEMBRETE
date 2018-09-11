package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.model.Lembrete;

public class LembreteDao implements DAO<Lembrete> {

	private EntityManager manager;

	@Inject
	public LembreteDao(EntityManager manager) {
		this.manager = manager;
	}

	public List<Lembrete> findAll() {
		return manager.createQuery("from lembrete").getResultList();
	}

	public Lembrete findById(Integer id) {
		Query query = manager.createQuery("from lembrete where id = :pId");
		query.setParameter("pId", id);
		return (Lembrete) query.getSingleResult();
	}

	public List<Lembrete> findByName(String descricao) {
		Query query = manager.createQuery("from lembrete where descricao like :pDescricao");
		query.setParameter("pDescricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public boolean save(Lembrete lembrete) {
		manager.persist(lembrete);
		return true;
	}

	public boolean delete(Integer id) {
		Lembrete lembrete = findById(id);
		manager.remove(lembrete);
		return true;
	}

}