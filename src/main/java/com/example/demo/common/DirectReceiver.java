package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
@RabbitListener(queues = "PtwoDirectQUE")
public class DirectReceiver {

    @Autowired
    ApplicationContextProvider applicationContextProvider;

    @RabbitHandler
    public void process(JSONObject messageJson) {
        System.out.println("参与方B的DirectReceiver消费者收到消息  : " + messageJson);
        Object object = applicationContextProvider.getBean(messageJson.getString("beanName"));
        Class clazz = null;
        try {
            clazz = Class.forName("com.example.demo.entity.Task");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object paramObject = JSON.parseObject(messageJson.getString("paramObject"), clazz);
        Method method = ReflectionUtils.findMethod(object.getClass(), messageJson.getString("methodName"), clazz);
        if (method == null) {
            System.out.println("The method can not find");
            return;
        }
        ReflectionUtils.invokeMethod(method, object, paramObject);
    }

}
