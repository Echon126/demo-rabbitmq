package com.web.mq.example.test;

import com.web.mq.example.service.receive.fanout.FanoutSender;
import com.web.mq.example.service.send.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestProduce implements ApplicationListener<ContextRefreshedEvent> {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    Producer producer;

    @Autowired
    FanoutSender sendService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {

        for (int i = 0; i < 10; i++) {
            producer.send("------------11111111111111111111111111-------------" + sdf.format(new Date()));
        }

        for (int i = 0; i < 2000; i++) {
/*
        for(int i=0;i<2000;i++){
            sendService.send("------------11111111111111111111111111-------------" + new Date());
        }*/
        }

   /* public static void main(String[] args) throws IOException {
        User uesr = new User();
        uesr.setAge(10);
        //uesr.setUserName("aaaa");
        ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(uesr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Map map = null;
            try {
                map = objectMapper.readValue(json, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(json);
        System.out.println(map);

    }*/
    }
}
