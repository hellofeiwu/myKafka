package com.imooc;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

public class MyConsumerInterceptor implements ConsumerInterceptor {
    @Override
    public ConsumerRecords onConsume(ConsumerRecords consumerRecords) {
        System.out.println("我要开始处理消息了！");
        return consumerRecords;
    }

    @Override
    public void close() {

    }

    @Override
    public void onCommit(Map map) {
        System.out.println("我要开始做 commit 动作了！");
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
