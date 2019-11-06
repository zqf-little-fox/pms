package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.AnalysisService;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/fileList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> jsonFileList(){
        return projectService.jsonFileList();
    }

    @RequestMapping(value = "/info/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public Project info(@PathVariable("pid") Integer pid){
        return projectService.getProjectById(pid);
    }

    @RequestMapping(value = "/proInList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> jsonProInAnalList(){
        return projectService.proInList();
    }

    @RequestMapping(value = "/proList", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> jsonProNotAnalList(){
        return projectService.proNotInList();
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword, Integer orderby){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> list = projectService.search(cid, keyword, orderby);
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = projectService.deleteProjectByPid(ids);
        Map<String, Object> map = new HashMap<>();
        if (result == true){
            map.put("statusCode",200);
        }else{
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String updateProject(Project project){
        projectService.updateProject(project);
        return "redirect:/pro/list";
    }

    @RequestMapping(value = "/edit/{pid}",method = RequestMethod.GET)
    public String edit(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project = projectService.getProjectById(pid);
        map.put("project",project);
        return "project-base-edit";
    }

    @RequestMapping(value = "/detail/{pid}",method = RequestMethod.GET)
    public String detail(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project = projectService.getProjectById(pid);
        map.put("project",project);
        return "project-base-look";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> list = projectService.getProjectList();
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/pro/list";
    }
}
