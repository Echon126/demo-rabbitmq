package com.web.mq.example.senior;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderMessage {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String message){
        this.amqpTemplate.convertAndSend("hello",message);
    }
}
