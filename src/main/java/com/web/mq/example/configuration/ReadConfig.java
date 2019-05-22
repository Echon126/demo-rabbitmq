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
     */

    /**
     * rabbitMQ有三种类型 direct、topic、fanout，header四种类型
     *  direct 类型是交换机和路由key完全匹配进行绑定的，简单来说就是“先匹配，再投递”，
     *          即在绑定时设定一个 routing_key，消息的routing_keyp匹配时，才会被交换器投送到绑定的队列中去，
     *          属于精确匹配类型
     *  top    通过交换机和模糊匹配路由的key绑定，按规则转发消息
     *  fanout 广播机制使用，通过交换机绑定的
     *  headers 设置header attribute参数类型的交换机
     *
     *
     *  RabbitMQ 的队列类型有两种 ，及时队列和延时队列
     *  即时队列中的消息会被立即消费
     *  延时队列中的消息会在指定的时间延迟之后被消费
     *
     */
}























