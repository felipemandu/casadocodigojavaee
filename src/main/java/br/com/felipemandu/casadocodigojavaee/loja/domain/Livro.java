package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	@Lob
	private String descricao;
	private Integer numeroPaginas;
	private BigDecimal preco;

}
