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

	public List<Livro> ultimosLancamentos() {
		String query = "SELECT l FROM Livro l JOIN FETCH l.autores ORDER BY l.id";
		return manager.createQuery(query, Livro.class).setFirstResult(5).getResultList();
	}

	public List<Livro> listarOutros() {
		String query = "SELECT l FROM Livro l JOIN FETCH l.autores ORDER BY l.id";
		return manager.createQuery(query, Livro.class).setMaxResults(5).getResultList();
	}

	public Livro getById(Integer id) {
		String query = "SELECT l FROM Livro l JOIN FETCH l.autores WHERE l.id = :id";
		return manager.createQuery(query, Livro.class).setParameter("id", id).getSingleResult();
	}

}
