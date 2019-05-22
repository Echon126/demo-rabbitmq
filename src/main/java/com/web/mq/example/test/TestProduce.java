package com.web.mq.example.test;

import com.web.mq.example.service.send.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestProduce implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    Producer producer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {

        for (int i = 0; i < 10; i++) {
            producer.send("------------11111111111111111111111111-------------" + new Date());
        }
    }
}
