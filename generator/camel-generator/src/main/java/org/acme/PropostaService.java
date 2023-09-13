package org.acme;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class PropostaService {

    private String[] corretores = { "Ramalho", "Sampaio", "Bruno Rosseto", "Thiago Araki", "Braga", "Gilson" };
    private String[] clientes = { "Rodrigo", "Rafael", "Ian", "Carol", "Samara", "Rebeca" };

    private final AtomicInteger counter = new AtomicInteger();

    private final Random random = new Random();
    
    public Proposta generateOrder() {
        Proposta proposta = new Proposta();
        proposta.setAprovada(false);
        proposta.setId(counter.incrementAndGet());
        proposta.setValor(Double.valueOf(random.nextInt(5000-500) + 500));
        proposta.setCliente(getCliente());
        proposta.setCorretor(getCorretor());
        return proposta;
    }

    public String getCliente(){
      return clientes[random.nextInt(this.clientes.length)];
    }

    public String getCorretor(){
      return corretores[random.nextInt(this.corretores.length)];
    }

}