package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.SourcesService;
import com.ujiuye.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity delete(Integer id){
        sourcesService.deleteSources(id);
        return ResultEntity.success();
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public ModelAndView getSourcesInfo(@PathVariable("id")Integer id){
        Sources sources = sourcesService.getSourcesInfo(id);
        ModelAndView mv = new ModelAndView("pm-edit");
        mv.addObject("onesource",sources);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Sources sources) {
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){
        List<Sources> list = sourcesService.getSourcesById(0);
        queryChildren(list.get(0));
        return list;
    }

    //完成递归查询
    private void queryChildren(Sources sources) {
        Integer id = sources.getId();
        List<Sources> list = sourcesService.getSourcesById(id);
        for (Sources sources1 : list) {
            queryChildren(sources1);
        }
        sources.setChildren(list);
    }

}
