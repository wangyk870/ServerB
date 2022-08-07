package com.example.demo.mapper;

import com.example.demo.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {

    @Insert("insert into b_task (task_name, script_name, task_json) " +
            "values (#{taskName}, #{scriptName}, #{taskJson})")
    int createTask(Task task);
}
