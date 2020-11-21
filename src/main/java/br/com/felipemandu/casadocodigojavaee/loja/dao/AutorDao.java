package br.com.felipemandu.casadocodigojavaee.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.felipemandu.casadocodigojavaee.loja.domain.Autor;

public class AutorDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void save(Autor autor) {
		manager.persist(autor);
	}
	
	public Autor getById(Integer id) {
		return manager.find(Autor.class, id);
	}
	
	public List<Autor> getAll() {
		String query = "Select a from Autor a";
		return manager.createQuery(query, Autor.class).getResultList();
	}
	
}
