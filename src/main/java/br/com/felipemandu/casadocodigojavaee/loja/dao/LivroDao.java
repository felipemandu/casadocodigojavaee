package br.com.felipemandu.casadocodigojavaee.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void save(Livro livro) {
		manager.persist(livro);
	}
	
	public Livro getById(Integer id) {
		return manager.find(Livro.class, id);
	}
	
}
