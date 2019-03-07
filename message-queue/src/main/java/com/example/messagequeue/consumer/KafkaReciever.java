package com.example.messagequeue.consumer;

import com.example.messagequeue.model.SharedItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaReciever {

    @Value("${spring.kafka.topic.test}")
    private String testTopic;

    @KafkaListener(topics = "${spring.kafka.topic.test}")
    public void receive(SharedItem sharedItem) {

    }
}