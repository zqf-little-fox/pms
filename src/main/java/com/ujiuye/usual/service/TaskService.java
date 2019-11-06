package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    void saveInfo(Task task);

    PageInfo<Task> getMyTaskList(Integer eid, Integer pageNum, Map<String, Object> parameterMap);

    PageInfo<Task> getTaskList(Integer pageNum, Map<String, Object> parameterMap);

    boolean startTask(Integer id,Integer status);

    Task getTaskById(Integer id);

    void updateTask(Task task);
}
