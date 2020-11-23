package br.com.felipemandu.casadocodigojavaee.loja.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

@ApplicationScoped
public class FacesContextProducer {

	@Produces
	@RequestScoped
	public FacesContext contexto() {
		return FacesContext.getCurrentInstance();
	}
}
