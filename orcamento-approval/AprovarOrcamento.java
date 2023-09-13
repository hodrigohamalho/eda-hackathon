import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class AprovarOrcamento extends RouteBuilder {
  
  @Override
  public void configure() throws Exception {

    from("kafka:orcamentos")
      .log("orçamentos...")
      
  }
  
  
}






    












// from("telegram:bots")
//   .setHeader("CamelHttpMethod", constant("GET"))
//   .to("https://api.chucknorris.io/jokes/random")
//   .unmarshal().json(JsonLibrary.Jackson)
//   .transform(simple("${body[value]}"))
// .to("telegram:bots");
