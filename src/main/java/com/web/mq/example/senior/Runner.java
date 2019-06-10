package com.web.mq.example.senior;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.web.mq.example.senior.Configuration.ExchangeName;
import static com.web.mq.example.senior.Configuration.performanceQueueName;
import static com.web.mq.example.senior.Configuration.pointQueueName;

@Component
public class Runner  implements CommandLineRunner {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message>>>");
        rabbitTemplate.convertAndSend(ExchangeName, pointQueueName, "增加积分~");
        rabbitTemplate.convertAndSend(ExchangeName, performanceQueueName, "增加业绩~");
    }
}
