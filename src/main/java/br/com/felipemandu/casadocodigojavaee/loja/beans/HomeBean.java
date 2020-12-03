package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

@Model
public class HomeBean {
	
	@Inject
	private LivroDao livroDao;
	
	public List<Livro> getUltimosLancamentos() {
		return  livroDao.ultimosLancamentos();
	}
	
	public List<Livro> getListarOutros() {
		return livroDao.listarOutros();
	}

}
