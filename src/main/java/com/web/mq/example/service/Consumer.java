package com.web.mq.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RabbitListener(queues = "okong")
public class Consumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static List<String> list = new ArrayList<String>();

    @RabbitHandler
    public void process(String messages, Channel channel, Message message) throws IOException {
        // throw  new RuntimeException(messages);
        try {
            logger.info("接收的消息为: {}", messages);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("------------------receiver fail------------------------------------");
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }

    }
}
















