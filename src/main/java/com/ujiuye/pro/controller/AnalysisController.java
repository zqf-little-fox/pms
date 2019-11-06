package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/anal")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/analListById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Analysis getAnalListById(@PathVariable("id") Integer id){
        return analysisService.getAnalysisById(id);
    }

    @RequestMapping(value = "/analList",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> analJsonList(){
        return analysisService.getAnalysisList();
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        boolean result = analysisService.batchDelete(ids);
        if (result == true){
            map.put("statusCode",200);
        }else{
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Analysis analysis){
        analysisService.updateAnal(analysis);
        return "redirect:/anal/list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id")Integer id, Map<String,Object> map){
        Analysis analysis = analysisService.getAnalysisById(id);
        map.put("analysis",analysis);
        return "project-need-edit";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id, Map<String,Object> map){
        Analysis analysis = analysisService.getAnalysisById(id);
        map.put("analysis",analysis);
        return "project-need-look";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getNeedList(){
        List<Analysis> list = analysisService.getAnalysisList();
        ModelAndView mv = new ModelAndView("project-need");
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Analysis analysis){
        analysisService.saveInfo(analysis);
        return "redirect:/anal/list";
    }
}
