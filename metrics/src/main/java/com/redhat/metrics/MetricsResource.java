package com.redhat.metrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.smallrye.common.annotation.Blocking;

@Path("/events")
public class MetricsResource {

    @Inject
    MeterRegistry registry;

    @Inject
    Logger logger;

    @Inject
    @Channel("events")
    Emitter<Proposta> eventEmitter;

    Map<String, MelhorProposta> melhoresPropostasAprovadas = Collections.synchronizedMap(new HashMap<>());

    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Blocking
    public void get() {
        logger.debug("GreetingResource.get()");
    }

    @Incoming("propostas")
    public void consume(Proposta proposta) {
        System.out.println("reading proposta from Kafka");
        System.out.println(proposta);
        createMetrics(proposta.getCorretor(), proposta.getValor(), false);
    }

    @Incoming("propostasaprovadas")
    public void propostasaprovadas(Proposta proposta) {
        System.out.println("reading proposta APROVADA from Kafka");
        System.out.println(proposta);
        createMetrics(proposta.getCorretor(), proposta.getValor(), true);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void post(
            @FormParam("corretor") String corretor, @FormParam("valor") Double valor,
            @FormParam("aprovada") Boolean aprovada) {
        System.out.println("corretor: "+corretor);
        System.out.println("valor: "+valor);
        System.out.println("aprovada: "+aprovada);
        createMetrics(corretor, valor, aprovada);
    }

    private void createMetrics(String corretor, Double valor, Boolean aprovada) {
        List<Tag> tags = initTags(corretor, valor);

        if(aprovada) {
            registry.counter("com.redhat.metrics.propostas.aprovadas", tags).increment();
            maxAprovada(corretor, valor);
        }else {
            registry.counter("com.redhat.metrics.propostas", tags).increment();
        }
    }

    private List<Tag> initTags(String corretor, Double valor) {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("corretor", "" + corretor));
        tags.add(Tag.of("valor", valor.toString()));
        return tags;
    }

    private void maxAprovada(String corretor, Double valor) {
        List<Tag> tags = new ArrayList<>();
        // change user per name?
        tags.add(Tag.of("corretor", "" + corretor));
        tags.add(Tag.of("valor", valor.toString()));
        MelhorProposta scoreObj = new MelhorProposta(valor);
        MelhorProposta scoreMapped = melhoresPropostasAprovadas.get(corretor);
        if (scoreMapped == null) {
            melhoresPropostasAprovadas.put(corretor, scoreObj);
            Gauge.builder("com.redhat.propostas.melhores", scoreObj, (v) -> {
                return v.getValue();
            }).tags(tags).register(registry);
        } else {
            scoreMapped.setMaxValue(valor);
        }
    }

}