package com.example.messagequeue.controller;


import com.example.messagequeue.consumer.KafkaReciever;
import com.example.messagequeue.model.SharedItem;
import com.example.messagequeue.service.KafkaStreamProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    @Autowired
    private KafkaReciever receiver;

    private KafkaStreamProducer kafkaProducer;

    public TimeLineController(KafkaStreamProducer kafkaProducer) {
        this.kafkaProducer = new KafkaStreamProducer();
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void getAllSharedItem() {


    }



    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendSharedItem(@RequestBody SharedItem sharedItem) {

        SharedItem model = new SharedItem();
        model.id = sharedItem.id;
        model.title = sharedItem.title;
        model.content = sharedItem.content;
        kafkaProducer.send(model);
    }
}
