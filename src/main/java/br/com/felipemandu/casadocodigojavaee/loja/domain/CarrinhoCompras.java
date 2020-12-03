package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Set<CarrinhoItem> itens = new HashSet<>();
	
	
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

}
