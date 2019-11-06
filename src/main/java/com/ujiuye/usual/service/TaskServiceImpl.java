package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.bean.TaskExample;
import com.ujiuye.usual.mapper.TaskMapper;
import com.ujiuye.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    @Override
    public void saveInfo(Task task) {
        task.setStatus(0);
        taskMapper.insert(task);
    }

    @Override
    public PageInfo<Task> getTaskList(Integer pageNum, Map<String, Object> parameterMap) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();

        Map<String, String> myBatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        String status = myBatisMap.get("status");
        if (status != null && status != ""){
            criteria.andStatusEqualTo(Integer.valueOf(status));
        }
        String type = myBatisMap.get("type");
        String keyword = myBatisMap.get("keyword");
        if (keyword != null && keyword != "") {
            List<Task> eids = taskMapper.getEmpFkByEname(keyword);
            Integer eid = -1;
            for (Task eid1 : eids) {
                eid = Integer.valueOf(eid1.getEmployee().getEname());
            }
            if (type != null && type != "") {
                if (type.equals("1")) {
                    criteria.andTasktitleLike(keyword);
                } else if (type.equals("2")) {
                    criteria.andEmpFk2EqualTo(eid);
                }
            }else {
                criteria.andTasktitleLike(keyword);
                TaskExample.Criteria criteria1 = example.createCriteria();
                criteria1.andEmpFk2EqualTo(eid);
                example.or(criteria1);
            }
        }
        String orderby = myBatisMap.get("orderby");
        if (orderby != null && orderby != ""){
            if (orderby.equals("0")){
                example.setOrderByClause("starttime");
            }else if (orderby.equals("1")){
                example.setOrderByClause("endtime");
            }
        }
        PageHelper.startPage(pageNum, 5);
        List<Task> tasks = taskMapper.selectByExample(example);
        PageInfo<Task> page = new PageInfo<>(tasks,5);
        return page;
    }

    @Override
    public boolean startTask(Integer id,Integer status) {
        int row = taskMapper.updateStatus(status, id);
        return row == 1;
    }

    @Override
    public Task getTaskById(Integer id) {
        Task task = taskMapper.getTaskById(id);
        return task;
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public PageInfo<Task> getMyTaskList(Integer eid, Integer pageNum, Map<String, Object> parameterMap) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFk2EqualTo(eid);

        Map<String,String> mybatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        String status = mybatisMap.get("myStatus");
        if (status != null && status != ""){
            criteria.andStatusEqualTo(Integer.valueOf(status));
        }
        String type = mybatisMap.get("type");
        String keyword = mybatisMap.get("myKeyword");
        if (keyword != null && keyword != "") {
            if (type != null && type != "") {
                if (Integer.valueOf(type) == 1) {
                    criteria.andTasktitleLike(keyword);
                } else if (Integer.valueOf(type) == 2) {
                    criteria.andRemarkLike(keyword);
                }
            }else {
                criteria.andTasktitleLike(keyword);
                TaskExample.Criteria criteria1 = example.createCriteria();
                criteria1.andRemarkLike(keyword);
            }
        }
        String orderby = mybatisMap.get("myOrderby");
        if (orderby != null && orderby != ""){
            if (Integer.valueOf(orderby) == 0){
                example.setOrderByClause("starttime");
            }else if (Integer.valueOf(orderby) == 1){
                example.setOrderByClause("endtime");
            }
        }
        PageHelper.startPage(pageNum, 5);
        List<Task> tasks = taskMapper.selectByExample(example);
        PageInfo<Task> page = new PageInfo<>(tasks,5);
        return page;
    }
}
