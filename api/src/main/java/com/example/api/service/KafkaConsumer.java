package com.example.api.service;

import com.example.api.model.SharedItem;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Component
public class KafkaConsumer {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "test";
    private static final String GROUP_ID = "test.group";

    private ReceiverOptions<String, SharedItem> receiverOptions;
    private Map<String, Object> props = new HashMap<>();

   public KafkaConsumer(){
       props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
       props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
       props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
       props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
       props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.api.model");
       props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
       receiverOptions = ReceiverOptions.create(props);
   }


   public Flux<SharedItem> getSharedItemStream(){
       ReceiverOptions<String, SharedItem> options =  ReceiverOptions.<String, SharedItem>create(props)
               .subscription(Collections.singleton(TOPIC));

       Flux<SharedItem> sharedItemFlux =  KafkaReceiver.create(options).receiveAutoAck().concatMap(r -> r)
               .map(receiverRecord -> receiverRecord.value());

       return  sharedItemFlux.publish().autoConnect();
   }
}
