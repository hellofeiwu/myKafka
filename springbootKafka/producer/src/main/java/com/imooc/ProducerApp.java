package com.imooc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @Autowired
    MyService myService;

    @Override
    public void run(String... args) throws Exception {
        for(int i =0; i<3; i++){
            myService.sendMessage("Tom " + i);
        }
    }
}
