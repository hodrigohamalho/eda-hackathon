import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class PropostaToApolice extends RouteBuilder {
  
  @Override
  public void configure() throws Exception {

    from("telegram:bots")
      .convertBodyTo(String.class)
      .choice()
        .when(simple("${body} startsWith 'aprovar proposta'"))
          .log("aprovar proposta: ${body}")
          .to("kafka:apolicez")
        .otherwise()
            .setBody().simple("Ações:\n ➡️ aprovar proposta <id>")
            .to("telegram:bots");
      
  }
  
}






    












// from("telegram:bots")
//   .setHeader("CamelHttpMethod", constant("GET"))
//   .to("https://api.chucknorris.io/jokes/random")
//   .unmarshal().json(JsonLibrary.Jackson)
//   .transform(simple("${body[value]}"))
// .to("telegram:bots");
