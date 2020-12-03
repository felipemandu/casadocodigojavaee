package br.com.felipemandu.casadocodigojavaee.loja.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.felipemandu.casadocodigojavaee.loja.domain.Pagamento;

public class PagamentoGateway implements Serializable {
	private static final long serialVersionUID = 1661903439956655696L;

	public String pagar(BigDecimal total) {
		
		Client client = ClientBuilder.newClient();
		Pagamento pagamento = new Pagamento(total);
		String target = "http://book-payment.herokuapp.com/payment";
		Entity<Pagamento> json = Entity.json(pagamento);
		return client.target(target).request().post(json, String.class);
	}
}
