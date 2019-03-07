package com.example.messagequeue.service;
import com.example.messagequeue.model.SharedItem;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Component
public class KafkaStreamConsumer {

//    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
//    private static final String TOPIC = "test";
//    private static final String GROUP_ID = "test.group";
//
//    private ReceiverOptions<String, SharedItem> receiverOptions;
//    private Map<String, Object> props = new HashMap<>();

    public KafkaStreamConsumer() {
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.messagequeue.model");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        receiverOptions = ReceiverOptions.create(props);
    }


//    public ConsumerRecords<String, SharedItem> getSharedItemStream() {
//        Consumer<String, SharedItem> consumer = new KafkaConsumer<String, SharedItem>(props);
//        consumer.subscribe(Collections.singletonList(TOPIC));
//
//
//        ConsumerRecords<String, SharedItem> consumerRecords =
//                consumer.poll(1000);
//
//
//        return consumerRecords;
//    }
}
