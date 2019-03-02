package com.example.api.service;

import com.example.api.model.SharedItem;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducer {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "test";
    private static final String GROUP_ID = "test.group";

    private KafkaSender<String, SharedItem> sender;
    private SenderOptions<String, SharedItem> senderOptions;
    private Map<String, Object> props = new HashMap<>();

    public  KafkaProducer(){
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.api.model");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        senderOptions = SenderOptions.create(props);
        sender= KafkaSender.create(senderOptions);

    }

    public Mono<SharedItem> send(SharedItem sharedItem){
        SenderRecord<String,SharedItem,String> senderRecord = SenderRecord.create(new ProducerRecord<>("test",sharedItem.Id, sharedItem),sharedItem.Id);
        return sender.send(Mono.just(senderRecord)).next().map(any -> sharedItem);
    }
}
