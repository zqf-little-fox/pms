package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.service.ArchivesService;
import com.ujiuye.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/archives")
public class archivesController {
    @Autowired
    private ArchivesService archivesService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getArchivesList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        String requestURI = request.getRequestURI();
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        Map<String, String> searchString = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        PageInfo<Archives> page = archivesService.getArchivesList(pageNum,parameterMap);
        ModelAndView mv = new ModelAndView("archives");
        mv.addObject("requestURI",requestURI);
        mv.addObject("searchString",searchString);
        mv.addObject("page",page);
        return mv;
    }
}
