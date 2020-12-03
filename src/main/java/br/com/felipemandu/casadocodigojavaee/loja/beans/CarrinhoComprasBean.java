package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.felipemandu.casadocodigojavaee.loja.dao.LivroDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.CarrinhoCompras;
import br.com.felipemandu.casadocodigojavaee.loja.domain.CarrinhoItem;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Livro;

@Model
public class CarrinhoComprasBean {
	
	@Inject
	private CarrinhoCompras carrinho;
	
	@Inject 
	private LivroDao dao;
	
	public String adicionar(Livro livro) {
		livro = dao.getById(livro.getId());
		carrinho.add(new CarrinhoItem(livro));;
		return "carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}
	
	public BigDecimal getTotal() {
		return carrinho.valorTotal();
	}

}
