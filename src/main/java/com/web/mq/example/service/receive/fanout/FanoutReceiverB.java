package com.web.mq.example.service.receive.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class FanoutReceiverB {
    private static final Logger logger = LoggerFactory.getLogger(FanoutReceiverB.class);
    @RabbitListener(queues = "fanout.B")
    public void process(String message) {
        logger.info("-----------fanout Receiver B :---------------" + message);

    }
}
