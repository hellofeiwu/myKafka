package com.imooc;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;

public class MyConsumer {
    public static void main(String[] args) {
        String TOPIC_NAME = "default_topic";
        String TRUSTSTORE_PASSWORD = "123456";

        String sasl_username = "avnadmin";
        String sasl_password = "AVNS_qSH1VzUsyh5Ox5CqvDT";
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasConfig = String.format(jaasTemplate, sasl_username, sasl_password);

        Properties properties = new Properties();

        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "SCRAM-SHA-256");
        properties.setProperty("sasl.jaas.config", jaasConfig);
        properties.setProperty("ssl.endpoint.identification.algorithm", "");
        properties.setProperty("ssl.truststore.type", "jks");
        properties.setProperty("ssl.truststore.location", "client.truststore.jks");
        properties.setProperty("ssl.truststore.password", TRUSTSTORE_PASSWORD);

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-a944bd8-figo357159-63d6.f.aivencloud.com:20293");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"my-group");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        int loopCount = 0;
        while (loopCount<20){
            System.out.println("enter while loop: "+loopCount);
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
            for(TopicPartition p : records.partitions()){
                List<ConsumerRecord<String, String>> recordList = records.records(p);
                for(ConsumerRecord<String, String> r : recordList){
                    System.out.println("topic: "+r.topic() + ", partition id: "+r.partition() +", message: "+r.value());
                }
            }
            loopCount++;
        }
        consumer.close();
    }
}
