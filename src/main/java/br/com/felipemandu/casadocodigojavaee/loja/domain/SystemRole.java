package br.com.felipemandu.casadocodigojavaee.loja.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SystemRole {
	
	@Id
	private String name;
	
	@Deprecated
	public SystemRole() {}

	public SystemRole(String name) {
		this.name = name;
	}
}
