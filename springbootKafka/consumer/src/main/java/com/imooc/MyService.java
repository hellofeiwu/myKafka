package com.imooc;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;


@Component
public class MyService {

    @KafkaListener(groupId = "my-group", topics = "default_topic")
    public void doMessage(ConsumerRecord record){
        System.out.println("处理消息： " + record.value());
    }
}
