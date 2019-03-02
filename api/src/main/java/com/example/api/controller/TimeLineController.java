package com.example.api.controller;


import com.example.api.model.SharedItem;
import com.example.api.service.KafkaConsumer;
import com.example.api.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    @Autowired
    private final KafkaConsumer kafkaConsumer;

    @Autowired
    private final KafkaProducer kafkaProducer;

    public TimeLineController(KafkaConsumer kafkaConsumer, KafkaProducer kafkaProducer) {
        this.kafkaConsumer = new KafkaConsumer();
        this.kafkaProducer = new KafkaProducer();
    }


    public Flux<SharedItem> getAllSharedItem(){
        return kafkaConsumer.getSharedItemStream();
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void sendSharedItem(){
        SharedItem model = new SharedItem();
        model.Id = "32131";
        model.Title = "Baslik 1";
        model.Content = "dasdsdhgsdfa";
        kafkaProducer.send(model);
    }
}
