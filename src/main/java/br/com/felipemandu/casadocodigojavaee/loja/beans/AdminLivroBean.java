package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.felipemandu.casadocodigojavaee.loja.dao.AutorDao;
import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Autor;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;
import br.com.felipemandu.casadocodigojavaee.loja.infra.MessageHelper;

@Named
@RequestScoped
public class AdminLivroBean {

	private Livro livro = new Livro();

	private List<Integer> autoresId = new ArrayList<>();

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private MessageHelper msgHelper;
	
	public String salvar() {
		cadastreAutoresNoLivro(this.autoresId, this.livro);	
		livroDao.save(livro);
		msgHelper.addFlash(null, new FacesMessage("Livro cadastrado com sucesso."));
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
	
	private void cadastreAutoresNoLivro(List<Integer> autoresId, Livro livro) {
		List<Autor> autores = autoresId.stream()
				.map(id -> new Autor(id))
				.collect(Collectors.toList());
		
		livro.setAutores(autores);
	}

}
