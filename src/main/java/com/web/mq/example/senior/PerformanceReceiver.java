package com.web.mq.example.senior;

import org.springframework.stereotype.Component;

@Component
public class PerformanceReceiver {
    public void receivePerformanceMessage(String message) {
        System.out.println("接收到来自业绩队列的信息 <" + message + ">");
    }

    public void receivePointMessage(String message) {
        System.out.println("接收到来自积分队列的信息 <" + message + ">");
    }
}
