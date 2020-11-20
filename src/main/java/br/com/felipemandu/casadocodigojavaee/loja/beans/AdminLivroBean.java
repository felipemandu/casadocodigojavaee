package br.com.felipemandu.casadocodigojavaee.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

@Named
@RequestScoped
public class AdminLivroBean {
	
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao dao;
	
	public void salvar() {
		dao.save(livro);
		System.out.println("Livro esta sendo salvo" + livro.toString());
	}
	
	public void buscaPorId() {
		this.livro = dao.getById(this.livro.getId());
		System.out.println(livro.toString());
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	

}
