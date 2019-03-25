package com.example.stream.service;

import com.example.stream.model.SharedItem;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StreamService {

    @Autowired
    JavaInputDStream<ConsumerRecord<String, String>> stream;

    @Autowired
    JavaStreamingContext javaStreamingContext;

    @Autowired
    SparkSession sparkSession;

    public  void get(){

        stream.map(record -> record.value()).print();


        Collection<String> aa  = new ArrayList<String>();

        stream.foreachRDD(row ->  {
            row.map(x -> x.value());
        });





        javaStreamingContext.start();
        try {
            javaStreamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
