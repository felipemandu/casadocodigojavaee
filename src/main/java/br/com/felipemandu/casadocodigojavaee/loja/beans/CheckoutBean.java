package br.com.felipemandu.casadocodigojavaee.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.felipemandu.casadocodigojavaee.loja.domain.CarrinhoCompras;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Model
public class CheckoutBean {

	@Getter @Setter
	private Usuario usuario = new Usuario();
	
	@Inject
	private CarrinhoCompras carrinho;
	
	@Transactional
	public void finalizar() {
		carrinho.finalizar(usuario);
	}
	
}
