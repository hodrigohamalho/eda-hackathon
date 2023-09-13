package com.redhat.metrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

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

    // Map<String, Score> mapUserMaxScore = Collections.synchronizedMap(new HashMap<>());

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
    }


    // @POST
    // @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    // @Blocking
    // public CompletionStage<Void> post(
    //         @FormParam("kind") String kind, @FormParam("player") int player,
    //         @FormParam("user") String user, @FormParam("score") Integer score,
    //         @FormParam("userName") String userName,
    //         MultivaluedMap<String, String> form) {

    //     System.out.println("kind: "+kind);
    //     System.out.println("player: "+player);
    //     System.out.println("user: "+user);
    //     System.out.println("score: "+score);
    //     System.out.println("username: "+userName);
    //     createMetrics(kind, player, user, score, userName);

    //     //Send to Kafka
    //     Event event = new Event(kind, players[player], score, user, userName);
    //     return eventEmitter.send(event);
    // }

    // private void createMetrics(String kind, int player, String user, Integer score, String userName) {
    //     List<Tag> tags = initTags(kind, player, user, userName);

    //     // Generic counter
    //     registry.counter("com.redhat.metrics", tags).increment();

    //     // Counter per event kind
    //     registry.counter("com.redhat.metrics." + kind, tags).increment();

    //     System.out.println("Comparing the kind with game over");
    //     // Counter per Game Over/Score
    //     if ("game_over".equals(kind)) {
    //         System.out.println("Joined in the loop");
    //         // Its working for this Demo, =D.
    //         // Do not use in Prodution
    //         gameOverMetric(user, userName, score);
    //     }
    // }

    // private List<Tag> initTags(String kind, int player, String user, String userName) {
    //     List<Tag> tags = new ArrayList<>();

    //     tags.add(Tag.of("kind", "" + kind));
    //     tags.add(Tag.of("user", "" + user));
    //     tags.add(Tag.of("userName", userName));
    //     tags.add(Tag.of("player", players[player]));
    //     return tags;
    // }

    // private void gameOverMetric(String user, String userName, Integer score) {
    //     List<Tag> tags = new ArrayList<>();
    //     // change user per name?
    //     tags.add(Tag.of("user", "" + user));
    //     tags.add(Tag.of("userName", userName));
    //     Score scoreObj = new Score(score);
    //     String userKey = user+userName;
    //     Score scoreMapped = mapUserMaxScore.get(userKey);
    //     if (scoreMapped == null) {
    //         mapUserMaxScore.put(userKey, scoreObj);
    //         Gauge.builder("com.redhat.metrics.score", scoreObj, (v) -> {
    //             return v.getValue();
    //         }).tags(tags).register(registry);
    //     } else {
    //         scoreMapped.setMaxValue(score);
    //     }

    // }

}