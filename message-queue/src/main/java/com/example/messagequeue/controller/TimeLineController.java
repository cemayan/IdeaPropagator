package com.example.messagequeue.controller;

import com.example.messagequeue.model.SharedItem;
import com.example.messagequeue.service.KafkaStreamConsumer;
import com.example.messagequeue.service.KafkaStreamProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    @Autowired
    private  KafkaStreamConsumer kafkaStreamConsumer;

    @Autowired
    private KafkaStreamProducer kafkaStreamProducer;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendSharedItem(@RequestBody SharedItem sharedItem) {

        SharedItem model = new SharedItem();
        model.id = sharedItem.id;
        model.title = sharedItem.title;
        model.content = sharedItem.content;
        kafkaStreamProducer.send(model);
    }
}
