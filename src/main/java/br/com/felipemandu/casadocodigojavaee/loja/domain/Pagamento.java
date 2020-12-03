package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagamento {
	
	private BigDecimal value;

}
