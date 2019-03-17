package com.example.stream.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StreamService {

    @Autowired
    JavaInputDStream<ConsumerRecord<String, String>> stream;

    @Autowired
    JavaStreamingContext javaStreamingContext;

    public  void get(){

        stream.map(record -> record.value()).print();

        javaStreamingContext.start();
        try {
            javaStreamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
