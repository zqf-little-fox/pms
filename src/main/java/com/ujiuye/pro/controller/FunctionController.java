package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/func")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/funcList/{id}",method = RequestMethod.GET)
    @ResponseBody
    public List<Function> funcList(@PathVariable("id")Integer id){
        return functionService.getfuncNameList(id);
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDelete(@RequestParam("ids[]")Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        boolean result = functionService.batchDetele(ids);
        if (result == true){
            map.put("statusCode",200);
        }else{
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Function function){
        functionService.updateFunction(function);
        return "redirect:/func/list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Map<String,Object> map){
        Function function = functionService.getFunctionById(id);
        map.put("function",function);
        return "project-function-edit";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Map<String,Object> map){
        Function function = functionService.getFunctionById(id);
        map.put("function",function);
        return "project-function-look";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getFunctionList(){
        ModelAndView mv = new ModelAndView("project-function");
        List<Function> list = functionService.getFunctionList();
        mv.addObject("list",list);
       return mv;

    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Function function){
        functionService.saveInfo(function);
        return "redirect:/func/list";
    }
}
