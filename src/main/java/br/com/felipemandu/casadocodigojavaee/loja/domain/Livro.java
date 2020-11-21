package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

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
	@ManyToMany
	@JoinTable(name = "livro_autor", 
		joinColumns = @JoinColumn(name = "livros_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "autores_id", referencedColumnName = "id"))
	private List<Autor> autores;

}
