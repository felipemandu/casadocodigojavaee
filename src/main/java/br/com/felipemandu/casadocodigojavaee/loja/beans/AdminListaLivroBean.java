package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

@Model
public class AdminListaLivroBean {

	@Inject
	private LivroDao dao;

	public List<Livro> getLivros() {
		return dao.getAll();
	}
	
}
