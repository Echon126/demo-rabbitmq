package com.web.mq.example.service.receive;

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
public class Consumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static List<String> list = new ArrayList<String>();

    @RabbitListener(queues = "okong")
    public void process(String messages, Channel channel, Message message) throws IOException {
        try {
            list.get(0);
            logger.info("接收的消息为: {}", messages);
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("------------------receiver fail------------------------------------",e);
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }


    //TODO 不加(try catch) 消费者会一直抛出栈信息，知道内存溢出，加(try catch)会打印一次栈信息，
    //TODO 消费会被当做已经消费，从队列中剔除掉
}
















