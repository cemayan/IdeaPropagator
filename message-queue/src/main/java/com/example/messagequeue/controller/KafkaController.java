package com.example.messagequeue.controller;

import com.example.messagequeue.model.SharedItem;
import com.example.messagequeue.service.KafkaStreamConsumer;
import com.example.messagequeue.service.KafkaStreamProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class KafkaController {

    @Autowired
    private  KafkaStreamConsumer kafkaStreamConsumer;

    @Autowired
    private KafkaStreamProducer kafkaStreamProducer;

    @PostMapping()
    public void sendSharedItem(@RequestBody SharedItem sharedItem) {

        SharedItem model = new SharedItem();
        model.id = sharedItem.id;
        model.title = sharedItem.title;
        model.content = sharedItem.content;
        kafkaStreamProducer.send(model);
    }
}
