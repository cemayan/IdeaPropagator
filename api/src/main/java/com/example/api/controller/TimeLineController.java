package com.example.api.controller;


import com.example.api.model.SharedItem;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

//    private KafkaConsumer kafkaConsumer;
//
//    private KafkaStreamProducer kafkaProducer;
//
//    public TimeLineController(KafkaConsumer kafkaConsumer, KafkaStreamProducer kafkaProducer) {
//        this.kafkaConsumer = new KafkaConsumer();
//        this.kafkaProducer = new KafkaStreamProducer();
//    }
//
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String getAllSharedItem() {
//        return "dasda";
//        //return kafkaConsumer.getSharedItemStream();
//    }
//
//
//    @RequestMapping(value = "/send", method = RequestMethod.POST)
//    public void sendSharedItem(@RequestBody SharedItem sharedItem) {
//
//        SharedItem model = new SharedItem();
//        model.id = sharedItem.id;
//        model.title = sharedItem.title;
//        model.content = sharedItem.content;
//        kafkaProducer.send(model);
//    }
}
