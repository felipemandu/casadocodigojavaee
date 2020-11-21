package br.com.felipemandu.casadocodigojavaee.loja.dao;

import java.util.List;

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

	public List<Livro> getAll() {
		String query = "select distinct (l) From Livro l join fetch l.autores";
		return manager.createQuery(query, Livro.class).getResultList();
	}
	

	
}
