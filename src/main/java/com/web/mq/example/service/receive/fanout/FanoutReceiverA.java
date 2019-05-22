package com.web.mq.example.service.receive.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {
    private static final Logger logger = LoggerFactory.getLogger(FanoutReceiverA.class);

    @RabbitHandler
    public void process(String message) {
        logger.info("-----------fanout receiver A :---------------" + message);
    }
}
