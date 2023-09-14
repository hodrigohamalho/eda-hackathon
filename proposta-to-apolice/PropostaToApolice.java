import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class PropostaToApolice extends RouteBuilder {
  
  @Override
  public void configure() throws Exception {

    from("kafka:propostas-aprovadas")
      .log("reading proposta aprovada")
      .log("${body}")
    .to("kafka:apolice");

    from("telegram:bots")
      .convertBodyTo(String.class)
      .choice()
        .when(simple("${body} startsWith 'aprovar proposta'"))
          .log("aprovar proposta: ${body}")
          .to("kafka:apolice")
          .setBody().simple("proposta aprovada")
          .to("telegram:bots")
        .otherwise()
            .setBody().simple("Ações:\n ➡️ aprovar proposta <id>")
            .to("telegram:bots");
      
  }
  
}
