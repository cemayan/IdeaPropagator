package com.example.messagequeue.service;

import com.example.messagequeue.model.SharedItem;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaStreamProducer {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "test";

    private KafkaSender<String, SharedItem> sender;
    private SenderOptions<String, SharedItem> senderOptions;
    public Map<String, Object> props = new HashMap<>();


    public KafkaStreamProducer() {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.api.model");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        senderOptions = SenderOptions.create(props);
        sender = KafkaSender.create(senderOptions);

    }

    public void send(SharedItem sharedItem) {
        Producer<String, SharedItem> producer = new KafkaProducer<String, SharedItem>(props);

        ProducerRecord<String, SharedItem> data = new ProducerRecord<String, SharedItem>(
                TOPIC, sharedItem.id.toString(), sharedItem);
        producer.send(data);
    }
}
