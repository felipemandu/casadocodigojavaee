package br.com.felipemandu.casadocodigojavaee.loja.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.felipemandu.casadocodigojavaee.loja.domain.Compra;

public class CompraDao implements Serializable{
	private static final long serialVersionUID = -85959107796048857L;

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Compra compra) {
		manager.persist(compra);
	}
	
	public Compra buscaPorUuid(String uuid) {
		String jpql = "select c from Compra c where c.uuid = :uuid";
		return manager.createQuery(jpql, Compra.class).setParameter("uuid", uuid).getSingleResult();
	}
	
}
