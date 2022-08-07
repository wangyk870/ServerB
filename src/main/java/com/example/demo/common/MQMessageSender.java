package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQMessageSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public boolean sendMethodMessage(String routingKey, JSONObject methodJson) {
        rabbitTemplate.convertAndSend("TestDirectExchange", routingKey, methodJson);
        return true;
    }

}
