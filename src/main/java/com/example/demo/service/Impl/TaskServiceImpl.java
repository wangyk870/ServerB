package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.MQMessageSender;
import com.example.demo.entity.Task;
import com.example.demo.mapper.PartnerMapper;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    PartnerMapper partnerMapper;

    @Autowired
    MQMessageSender mqMessageSender;

    @Override
    public String createTask(Task task) {
        log.info(task.toString());
        JSONObject taskJson = JSONObject.parseObject(task.getTaskJson());
        List<String> partnerNames = (List<String>) taskJson.get("partner");
        List<String> partnerKeys = new ArrayList<>();
        for (String name : partnerNames) {
            partnerKeys.add(partnerMapper.getKeyByName(name));
        }
        System.out.println(partnerKeys);
        Class clazz = taskMapper.getClass();
        Method method = null;
        try {
            method = taskMapper.getClass().getMethod("createTask", Task.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("class: " + clazz);
        System.out.println("method: " + method);

        JSONObject methodJson = new JSONObject();
        methodJson.put("beanName", "taskMapper");
        methodJson.put("methodName", "createTask");
        methodJson.put("paramName", task.getClass().getName());
        methodJson.put("paramObject", JSONObject.toJSONString(task));

        for (String routingKey : partnerKeys) {
            mqMessageSender.sendMethodMessage(routingKey, methodJson);
        }
        int res = taskMapper.createTask(task);
        return  "" + res;
    }
}
