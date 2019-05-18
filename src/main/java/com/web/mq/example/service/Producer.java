package com.web.mq.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Producer implements RabbitTemplate.ReturnCallback {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    ;

/*    @Autowired
    AmqpTemplate rabbitmqTemplate;*/
private static List<String> list = new ArrayList<String>();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            System.out.println("-------------ack---------------"+ack);
            if (!ack) {
                logger.info("Producer消息发送失败" + cause + correlationData);
            } else {
                logger.info("Producer 消息发送成功");
            }
        }));
        rabbitTemplate.convertAndSend("okong", message);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("sender return success" + message.toString() + "===" + replyText + "===" + exchange + "===" + routingKey);

    }
}
