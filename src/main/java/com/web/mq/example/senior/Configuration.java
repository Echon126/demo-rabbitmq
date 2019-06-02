package com.web.mq.example.senior;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    public static final String ExchangeName="Spring-boot-exchange";
    public static final String pointQueueName="point";
    public static final String performanceQueueName="performance";

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(ExchangeName);
    }

    @Bean
    Queue performanceQueue() {
        return new Queue(performanceQueueName, false);
    }

    @Bean
    Queue pointQueue() {
        return new Queue(pointQueueName, false);
    }

    @Bean
    Binding bindingPerformance(Queue performanceQueue, DirectExchange exchange) {
        return BindingBuilder.bind(performanceQueue)
                .to(exchange)
                .with(performanceQueueName);
    }

    @Bean
    Binding bindingPointQueue(Queue pointQueue, DirectExchange exchange) {
        return BindingBuilder.bind(pointQueue)
                .to(exchange)
                .with(pointQueueName);
    }
}
