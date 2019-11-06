package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Model;
import com.ujiuye.pro.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mod")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "modNameList/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Model> modNameList(@PathVariable("pid")Integer pid){
        return modelService.getModNameList(pid);
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = modelService.batchDelete(ids);
        Map<String,Object> map = new HashMap<>();
        if (result == true){
            map.put("statusCode",200);
        }else{
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String updateModel(Model model){
        modelService.updateModel(model);
        return "redirect:/mod/list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Map<String,Object> map){
        Model model = modelService.getModelById(id);
        map.put("model",model);
        return "project-model-edit";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Map<String,Object> map){
        Model model = modelService.getModelById(id);
        map.put("model",model);
        return "project-model-look";
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Model model){
        modelService.saveInfo(model);
        return "redirect:/mod/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getModelList(){
        ModelAndView mv = new ModelAndView("project-model");
        List<Model> list = modelService.getModelList();
        mv.addObject("list",list);
        return mv;
    }
}
