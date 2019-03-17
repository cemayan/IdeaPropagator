package com.example.stream.config;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SparkConfiguration {

    @Value("${spark.appName}")
    private String appName;

    @Value("${spark.master}")
    private String master;

    @Value("${spark.duration}")
    private Integer duration;

    @Value("${kafka.bootstrapServers}")
    private String bootstrapServers;

    @Value("${kafka.groupId}")
    private String groupId;

    @Value("${kafka.topic}")
    private String topic;

    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf =
                new SparkConf()
                        .setAppName(appName)
                        .setMaster(master);

        return sparkConf;
    }



    @Bean
    public Map<String, Object> kafkaParams() {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", bootstrapServers);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        kafkaParams.put("group.id", "test-group");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        return  kafkaParams;
    }

    @Bean
    public JavaInputDStream<ConsumerRecord<String, String>> stream() {
        Collection<String> topics = Arrays.asList(topic);

        return   KafkaUtils.createDirectStream(
                javaStreamingContext(),
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams())
        );
    }


    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public JavaStreamingContext javaStreamingContext() {
        return new JavaStreamingContext(javaSparkContext(), Durations.seconds(duration));
    }

    @Bean
    public SparkSession sparkSession() {
        return  SparkSession
                .builder()
                .sparkContext(javaSparkContext().sc())
                .appName("asd")
                .getOrCreate();
    }


}
