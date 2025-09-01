package com.imooc;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-a944bd8-figo357159-63d6.f.aivencloud.com:20293");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,MySerializer.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());
        //properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,MyPartitioner.class.getName());

        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(properties);

        for(int i=0; i<3; i++){
            User user = new User();
            user.setId(i);
            user.setName("Tom "+i);
            ProducerRecord<String, User> record =
                    new ProducerRecord<>(TOPIC_NAME, user);

//            producer.send(record, new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                    if(e != null){
//                        e.printStackTrace();
//                    }
//                    System.out.println("===== sent ok! =====");
//                }
//            });
            Future f = producer.send(record);
            System.out.println(f.get());
        }
        producer.flush();
        producer.close();
    }
}
