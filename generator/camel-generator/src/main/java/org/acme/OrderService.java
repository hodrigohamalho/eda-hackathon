package org.acme;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {

    private String[] corretores = { "Ramalho", "Sampaio", "Bruno Rosseto", "Thiago Araki", "Braga", "Gilson" };
    private String[] clientes = { "Rodrigo", "Rafael", "Ian", "Carol", "Samara", "Rebeca" };

    private final AtomicInteger counter = new AtomicInteger();

    private final Random random = new Random();
    
    public String createOrder(Order order){
		  return "order: "+order.getId();
    }

    public Order generateOrder() {
        Order order = new Order();
        order.setAprovada(false);
        order.setId(counter.incrementAndGet());
        order.setValor(Double.valueOf(random.nextInt(5000-500) + 500));
        order.setCliente(getCliente());
        order.setCorretor(getCorretor());
        return order;
    }

    public String getCliente(){
      return clientes[random.nextInt(this.clientes.length)];
    }

    public String getCorretor(){
      return corretores[random.nextInt(this.corretores.length)];
    }

}