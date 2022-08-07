package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("task")
@Slf4j
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping("createTask")
    @ResponseBody
    public String createTask(String taskName, String scriptName, String json) {
        log.info("task message: " + taskName + " - " + scriptName + " - " + json);
        JSONObject taskJson = (JSONObject) JSONObject.parse(json);
        System.out.println(taskJson);
        Task task = new Task();
        task.setTaskName(taskName);
        task.setScriptName(scriptName);
        task.setTaskJson(taskJson.toJSONString());
        return taskService.createTask(task);
    }

}
