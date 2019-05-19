package com.web.mq.example.configuration;

public class ReadConfig {
    /**
     *
     *  spting config
     *  spring:
     *  rabbitmq:
     *  host: 127.0.0.1
     *  username: cloud-dev
     *  password: cloud-dev
     *  port: 5672
     *  virtual-host: /
     *  listener:
     *  simple:
     *  concurrency: 10 # Minimum number of consumers.
     *  max-concurrency: 20 # Maximum number of consumers.
     *  prefetch: 50
     *  retry:
     *  enabled: true #是否开启消费者重试（为false时关闭消费者重试，这时消费端代码异常会一直重复收到消息）
     *  max-attempts: 3
     *  initial-interval: 5000ms
     *  default-requeue-rejected: true  #默认true 重试次数超过上面的设置之后是否丢弃（false不丢弃时需要写相应代码将该消息加入死信队列）
     *  acknowledge-mode: manual        #关键    消费方手动ack
     *  direct:
     *  acknowledge-mode: manual        #关键    消费方手动ack
     *  publisher-returns: true             #关键    发送方的return与confirm模式保证信息发出成功
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
