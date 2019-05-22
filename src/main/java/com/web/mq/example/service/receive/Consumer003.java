/*
package com.web.mq.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "okong")
public class Consumer003 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String messages) {
        logger.info("接收的消息为: {}", messages);
        //throw  new RuntimeException(messages);
    }
}
*/
