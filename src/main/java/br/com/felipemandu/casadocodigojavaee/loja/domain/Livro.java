package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String subTitulo;
	
	@Lob
	@Length(min = 100)
	@NotBlank
	private String descricao;
	
	@Min(100)
	@NotNull
	private Integer numeroPaginas;
	
	@NotBlank
	private String isbn;
	
	
	@DecimalMin("20")
	@NotNull
	private BigDecimal preco;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataLancamento;

	@NotNull
	private String path;
	
	@ManyToMany
	@JoinTable(name = "livro_autor", 
		joinColumns = @JoinColumn(name = "livros_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "autores_id", referencedColumnName = "id"))
	private List<Autor> autores;
	

}
