package com.example.api.controller;


import com.example.api.model.SharedItem;
import com.example.api.service.KafkaConsumer;
import com.example.api.service.KafkaStreamProducer;
import org.bson.types.ObjectId;
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
    private final KafkaStreamProducer kafkaProducer;

    public TimeLineController(KafkaConsumer kafkaConsumer, KafkaStreamProducer kafkaProducer) {
        this.kafkaConsumer = new KafkaConsumer();

        this.kafkaProducer = new KafkaStreamProducer();
    }


//    public Flux<SharedItem> getAllSharedItem(){
//        return kafkaConsumer.getSharedItemStream();
//    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void sendSharedItem(){
        SharedItem model = new SharedItem();
        model._id = new ObjectId();
        model.title = "Baslik 1";
        model.content = "dasdsdhgsdfa";
        kafkaProducer.send(model);
    }
}
