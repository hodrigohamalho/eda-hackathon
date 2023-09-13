package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("timer:geradorOrcamento")
            .bean(OrderService.class, "generateOrder")
            .log("Orcamento do ${body.cliente} criado")
            .marshal().json(JsonLibrary.Jackson) // convert JSON
            .log("Enviando para o Kafka: ${body}")
            .to("kafka:orcamentos");
    }
    
}
