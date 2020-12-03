package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;

@Entity
@Data
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;
	
	private BigDecimal total;
	
	@Lob
	private String Itens;
	
	private String uuid;
	
	@PrePersist
	public void prePresist() {
		this.uuid = UUID.randomUUID().toString();
	}
	
}
