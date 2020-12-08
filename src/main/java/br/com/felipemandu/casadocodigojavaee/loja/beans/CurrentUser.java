package br.com.felipemandu.casadocodigojavaee.loja.beans;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.felipemandu.casadocodigojavaee.loja.dao.SecurityDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;

	@Inject
	private SecurityDao securityDao;

	private SystemUser systemUser;
	
	public SystemUser getSystemUser() {
		return systemUser;
	}
	
	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	public String logout() {
		request.getSession().invalidate();
		return "/livros/lista.xhtml?faces-redirect=true";
	}

	@PostConstruct
	private void loadSystemUser() {

		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = securityDao.findByEmail(email);
		}
	}

}
