package br.com.felipemandu.casadocodigojavaee.loja.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.felipemandu.casadocodigojavaee.loja.domain.CarrinhoCompras;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Compra;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Model
public class CheckoutBean {

	@Getter @Setter
	private Usuario usuario = new Usuario();
	
	@Inject
	private CarrinhoCompras carrinho;
	
	@Inject
	private FacesContext facesContext;
	
	@Transactional
	public void finalizar() {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		carrinho.finalizar(compra);
		
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", "services/pagamento?uuid=" + compra.getUuid());
		
	}
	
}
