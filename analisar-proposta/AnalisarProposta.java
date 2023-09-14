import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class AnalisarProposta extends RouteBuilder {
  
  @Override
  public void configure() throws Exception {

    from("kafka:propostas?groupId=aprovaProposta")
      .choice()
        .when().jq(".valor > 2000")
          .unmarshal().json()
          .log("valor: ${body['valor']} aprovada, enviando pro Kafka: orcamentos-aprovados")
          .transform().jq(".aprovada = true")
          .marshal().json(JsonLibrary.Jackson) // convert JSON
          .to("kafka:propostas-aprovadas")
        .otherwise()
          .to("kafka:propostas-reprovadas");
      
  }
  
}
