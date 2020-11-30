package br.com.felipemandu.casadocodigojavaee.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

@Model
public class LivroDetalheBean {

	@Inject
	private LivroDao dao;
	
	private Livro livro = new Livro();

	public void carregarDetalhe() {
		this.livro = dao.getById(livro.getId());
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
}
