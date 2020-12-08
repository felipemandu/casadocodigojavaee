package br.com.felipemandu.casadocodigojavaee.loja.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Email
	@NotBlank
	private String login;
	
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER )
	private List<SystemRole> roles = new ArrayList<>();
}
