package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CarrinhoItem {

	private Livro livro;
	@EqualsAndHashCode.Exclude
	private Integer quantidade;
	
	public CarrinhoItem(Livro livro) {
		this.livro = livro;
		this.quantidade = 1;
	}
	
	public BigDecimal getTotalPreco() {
		return livro.getPreco().multiply(new BigDecimal(quantidade));
	}

}
