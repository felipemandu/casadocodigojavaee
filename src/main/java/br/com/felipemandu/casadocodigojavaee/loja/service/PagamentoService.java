package br.com.felipemandu.casadocodigojavaee.loja.service;


import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.felipemandu.casadocodigojavaee.loja.dao.CompraDao;
import br.com.felipemandu.casadocodigojavaee.loja.domain.Compra;

@Path("/pagamento")
public class PagamentoService {

    @Context
    ServletContext context;

    @Inject
    private CompraDao compraDao;

    @Inject
    private PagamentoGateway pagamentoGateway;
    
    @Inject
    private JMSContext jmsContext;

    @Resource(name = "java:/jms/topics/CarrinhoComprasTopico")
    private Destination destination;
  
    private static ExecutorService executor = Executors.newFixedThreadPool(50);

    @POST
    public void pagar(@Suspended final AsyncResponse ar, 
    					@QueryParam("uuid") String uuid) {
        Compra compra = compraDao.buscaPorUuid(uuid);
        String contextPath = context.getContextPath();
        
        JMSProducer producer = jmsContext.createProducer();
        

        executor.submit(() -> {
            try {
                pagamentoGateway.pagar(compra.getTotal());
        
                producer.send(destination, compra.getUuid());
                String path = "http://localhost:8080" + contextPath + "/index.xhtml";
                String msg =  "Compra realizada com sucesso";
                
                URI responseUri = UriBuilder.fromPath(path).queryParam("msg", msg).build();
                Response response = Response.seeOther(responseUri).build();
                
                ar.resume(response);

            } catch (Exception e) {
                ar.resume(new WebApplicationException(e));
            }
        });
    }
}