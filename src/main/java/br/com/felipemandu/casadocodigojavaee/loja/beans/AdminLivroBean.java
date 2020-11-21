package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.felipemandu.casadocodigojavaee.loja.dao.AutorDao;
import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Autor;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

@Named
@RequestScoped
public class AdminLivroBean {

	private Livro livro = new Livro();

	private List<Integer> autoresId = new ArrayList<>();

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;

	public String salvar() {
		List<Autor> autores = autoresId.stream()
				.map(id -> new Autor(id))
				.collect(Collectors.toList());
		this.livro.setAutores(autores);
		livroDao.save(livro);
		return "/livros/lista?faces-redirect=true";
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return this.autorDao.getAll();
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

}
