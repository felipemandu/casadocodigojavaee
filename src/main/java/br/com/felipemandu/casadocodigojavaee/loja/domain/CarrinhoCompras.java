package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import br.com.felipemandu.casadocodigojavaee.loja.dao.CompraDao;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Set<CarrinhoItem> itens = new HashSet<>();
	
	@Inject
	private CompraDao compraDao;
	
	
	public void add(CarrinhoItem item) {
		itens.add(item);
	}
	
	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(itens);
	}
	
	public BigDecimal valorTotal() {
		return itens
				.stream()
				.map(v -> v.getTotalPreco())
				.reduce(BigDecimal.ZERO, (v1, v2) -> v1.add(v2));
	}

	public void finalizar(Usuario usuario) {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		compra.setItens(toJson(getItens()));
		compraDao.salvar(compra);
		
	}
	
	public Integer getQuantidadeTotal() {
		return itens.stream().mapToInt(v -> v.getQuantidade()).sum();
	}

	private String toJson(List<CarrinhoItem> itens) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for (CarrinhoItem item : itens) {
			JsonObjectBuilder value = Json.createObjectBuilder()
			.add("titulo", item.getLivro().getTitulo())
			.add("preco", item.getLivro().getPreco())
			.add("quantidade", item.getQuantidade())
			.add("total", item.getTotalPreco());
			builder.add(value);
		}
		return builder.build().toString();
	}

	public void remove(CarrinhoItem item) {
		itens.remove(item);
	}

}
