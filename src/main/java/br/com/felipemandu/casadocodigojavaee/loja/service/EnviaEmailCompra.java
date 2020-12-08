package br.com.felipemandu.casadocodigojavaee.loja.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.felipemandu.casadocodigojavaee.loja.dao.CompraDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Compra;
import br.com.felipemandu.casadocodigojavaee.loja.infra.MailSender;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup", 
				propertyValue = "java:/jms/topics/CarrinhoComprasTopico"), 
		@ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Topic")
		})
public class EnviaEmailCompra implements MessageListener {

	@Inject
	private MailSender mailSender;

	@Inject
	private CompraDao compraDao;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message; 
			Compra compra = compraDao.buscaPorUuid(textMessage.getText());
			mailSender.send("compras@casadocodigo.com", 
					compra.getUsuario().getEmail(), 
					"Nova compra na Cada do Código", 
					"Sua compra foi realizada com sucesso com número de pedido " + compra.getUuid());
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
		
	}

}
