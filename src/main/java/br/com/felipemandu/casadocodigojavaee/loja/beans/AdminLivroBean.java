package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.felipemandu.casadocodigojavaee.loja.dao.AutorDao;
import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Autor;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;
import br.com.felipemandu.casadocodigojavaee.loja.infra.FileAdmin;
import br.com.felipemandu.casadocodigojavaee.loja.infra.MessageHelper;

@Named
@RequestScoped
public class AdminLivroBean {

	private Livro livro = new Livro();

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private MessageHelper msgHelper;
	
	private Part capaLivro;

	@Transactional
	public String salvar() throws IOException {
		livro.setPath(new FileAdmin().write("livro", capaLivro));
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

	public Part getCapaLivro() {
		return capaLivro;
	}
	
	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}
	

}
