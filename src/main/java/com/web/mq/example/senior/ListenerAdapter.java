package com.web.mq.example.senior;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ListenerAdapter {
    @Bean
    MessageListenerAdapter pointListenerAdapter(PerformanceReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receivePointMessage");
    }

    @Bean
    MessageListenerAdapter performanceListenerAdapter(PerformanceReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receivePerformanceMessage");
    }

    @Bean
    SimpleMessageListenerContainer pointContainer(ConnectionFactory connectionFactory,
                                                  MessageListenerAdapter pointListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(Configuration.pointQueueName);
        container.setMessageListener(pointListenerAdapter);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer performanceContainer(ConnectionFactory connectionFactory,
                                                        MessageListenerAdapter performanceListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(Configuration.performanceQueueName);
        container.setMessageListener(performanceListenerAdapter);
        return container;
    }
}
