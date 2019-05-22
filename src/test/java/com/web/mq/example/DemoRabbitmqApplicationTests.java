package com.web.mq.example;

import com.web.mq.example.service.receive.fanout.FanoutSender;
import com.web.mq.example.service.send.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRabbitmqApplicationTests {

    @Autowired
    Producer producer;

    @Test
    public void contextLoads() {
       // for(int i=0;i<10000;i++){
        System.out.println("dfdsfsdfsdfdsfdssssssssssssssss");
            producer.send("------------11111111111111111111111111-------------" + new Date());
        //}
    }

    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSender() throws Exception {
        sender.send();
    }

}
