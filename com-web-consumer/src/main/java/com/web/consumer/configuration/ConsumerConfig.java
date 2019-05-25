package com.web.consumer.configuration;

import com.web.consumer.service.WebSocketServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.io.IOException;
@Configuration
@EnableRabbit
public class ConsumerConfig implements RabbitListenerConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private WebSocketServerEndpoint webSocketServerEndpoint;

    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer(@Qualifier("queueTransaction") Queue transaction, @Qualifier("queueContract") Queue contract, @Qualifier("queueQualification") Queue qualification) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(transaction, contract, qualification);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            byte[] body = message.getBody();
            logger.info("receive msg : " + new String(body));
            try {
                webSocketServerEndpoint.sendMessageToAll(new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//确认消息成功消费
            } catch (IOException e) {
                logger.error("消息推送前台出错：" + e.getMessage() + "/r/n重新发送");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); //重新发送
            }
        });
        return container;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(handlerMethodFactory());
    }


}
