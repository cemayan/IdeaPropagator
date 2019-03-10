package com.example.stream.controller;

import com.example.stream.model.SharedItem;
import org.apache.spark.streaming.Duration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SparkController {

   @GetMapping()
    public  void get(){



      SparkConf sparkConf = new SparkConf().setAppName("kafkaSparkStream").setMaster("local");
      JavaSparkContext jsc = new JavaSparkContext(sparkConf);
      JavaStreamingContext ssc = new JavaStreamingContext(jsc, new Duration(5000));



       Map<String, Object> kafkaParams = new HashMap<>();
       kafkaParams.put("bootstrap.servers", "localhost:9092");
       kafkaParams.put("key.deserializer", StringDeserializer.class);
       kafkaParams.put("value.deserializer", StringDeserializer.class);
       kafkaParams.put("group.id", "test-group");
       kafkaParams.put("auto.offset.reset", "latest");
       kafkaParams.put("enable.auto.commit", false);


      Collection<String> topics = Arrays.asList("test");

      final JavaInputDStream<ConsumerRecord<String, String>> stream =
              KafkaUtils.createDirectStream(
                      ssc,
                      LocationStrategies.PreferConsistent(),
                      ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
              );

      stream.map(record -> {
         return record.value().toString();
      }).print();

      ssc.start();

      try {
         ssc.awaitTermination();

      } catch (Exception e) {
         e.printStackTrace();
      }




   }
}
