package com.imooc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;



@Component
public class MyService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String value) {
        ListenableFuture f = kafkaTemplate.send("default_topic",value);
        f.addCallback(new ListenableFutureCallback() {

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println(o.toString());
            }
        });
    }
}
