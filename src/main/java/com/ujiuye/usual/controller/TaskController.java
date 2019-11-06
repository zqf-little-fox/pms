package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.service.TaskService;
import com.ujiuye.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String updateTask(Task task){
        taskService.updateTask(task);
        return "redirect:/task/list";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Map<String,Object> map){
        Task task = taskService.getTaskById(id);
        map.put("task",task);
        return "task-edit";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id,Map<String,Object> map){
        Task task = taskService.getTaskById(id);
        map.put("task",task);
        return "task-look";
    }

    @RequestMapping(value = "/startTask/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> startTask(@PathVariable("id") Integer id,@RequestParam(value = "status") Integer status){
        boolean result = taskService.startTask(id,status);
        Map<String,Object> map = new HashMap<>();
        if (true){
            map.put("statusCode",200);
        }else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/myList",method = RequestMethod.GET)
    public ModelAndView getMyTaskList(HttpServletRequest request,HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        String requestURI = request.getRequestURI();

        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String searchString = MapUtils.parseParameterMapToString(parameterMap);

        PageInfo<Task> page = taskService.getMyTaskList(eid,pageNum,parameterMap);

        ModelAndView mv = new ModelAndView("task-my");
        mv.addObject("page",page);
        mv.addObject("requestURI",requestURI);
        mv.addObject("searchString",searchString);
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getTaskList(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1") Integer  pageNum){
        String requestURI = request.getRequestURI();

        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String searchString = MapUtils.parseParameterMapToString(parameterMap);

        PageInfo<Task> page = taskService.getTaskList(pageNum,parameterMap);

        ModelAndView mv = new ModelAndView("task");
        mv.addObject("requestURI",requestURI);
        mv.addObject("searchString",searchString);
        mv.addObject("page",page);
        //List<Task> list = taskService.getTaskList();
        //mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Task task, HttpSession session){
        Employee employee = (Employee) session.getAttribute("loginUser");
        task.setEmpFk(employee.getEid());
        taskService.saveInfo(task);
        return "redirect:/task/list";
    }
}
