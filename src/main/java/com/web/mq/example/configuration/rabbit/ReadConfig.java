package com.web.mq.example.configuration.rabbit;

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

    /**
     * Exception
     *  org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException: Listener method 'no match' threw exception
     *  解决方式
     *      1.将@RabbitListener(queues=”test_mq_testQueue”)不需要放在类上。直接注释在方法上就好
     *       https://blog.csdn.net/olinner123/article/details/77477323
     *       https://www.cnblogs.com/lazio10000/p/5559999.html
     *       https://blog.csdn.net/liu941807382/article/details/86719159
     *
     * Caused by: org.springframework.amqp.AmqpException: No method found for class java.lang.String
     *              https://blog.csdn.net/u013358378/article/details/86495962
     *              消费者服务Service配置的队列名 如果重复，那么相同队列名的每个服务都会尝试去将当前的正在消费的消息转换成自己的入参对象
     *              （相当于一个生产者，多个消费者），结果很显然，消息转换入参对象不一样就报错啰，抛出的结果如上。
     *
     *              这里需要注意的是，同一Service里的消费方法是可以根据消息内容识别入参对象，也就是说队列名配置在类上是没有问题的(如下)，
     *              但同一队列名配置在多个类上就会有上述的问题，并且对上错误的类是将类里每个消费方法都尝试消息转换入参对象的，
     *              消费方法多的情况下会给人一种重复刷新的错觉。
     *
     */

    /**
     * TODO 消息手动确认模式的说明
     *  监听的方法内部必须使用channel进行消息确认，包括消费成功或者失败
     *  如果不手动确认，也不抛出异常，消费不会自动重新推送，因为对于rabbitmq来说始终没有接受到消息消费是否成功的确认，
     *  并且channel实在消费端有缓存的，没有断开连接
     *
     *
     * TODO https://my.oschina.net/dengfuwei/blog/1595047
     *
     * TODO https://my.oschina.net/dengfuwei/blog/1595044 spring-boot-rabbitmq动态管理
     */
}























