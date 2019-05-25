package com.web.mq.example.service.receive.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(String message) {
        String context = "hi,fanout message" + message;
        this.amqpTemplate.convertAndSend("fanoutExchange", "", context);
    }
}
