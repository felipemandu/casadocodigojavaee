package br.com.felipemandu.casadocodigojavaee.loja.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessageHelper {

	@Inject
	private FacesContext facesContext;
	
	public void addFlash(String clientId,FacesMessage message) {
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(clientId, message);
	}
	
	public void addMessage(String clientId, FacesMessage message) {
		facesContext.addMessage(clientId, message);
	}
	
}
