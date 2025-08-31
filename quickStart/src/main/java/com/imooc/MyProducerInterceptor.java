package com.imooc;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class MyProducerInterceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        String value = (String) producerRecord.value();
        return new ProducerRecord(producerRecord.topic(),"拦截器处理了 "+value);
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if(e !=null){
            e.printStackTrace();
        }else {
            System.out.println("收到kafka 返回 "+recordMetadata.toString());
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
