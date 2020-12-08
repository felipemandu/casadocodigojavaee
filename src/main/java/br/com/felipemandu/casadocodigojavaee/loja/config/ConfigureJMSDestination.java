package br.com.felipemandu.casadocodigojavaee.loja.config;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions({
	@JMSDestinationDefinition(
		name="java:/jms/topics/CarrinhoComprasTopico",
		interfaceName="javax.jms.Topic",
		destinationName = "java:/jms/topic/CarrinhoCompraTopico"
		)
})
@Singleton
public class ConfigureJMSDestination {

}
