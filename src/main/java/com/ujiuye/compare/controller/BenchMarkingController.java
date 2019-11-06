package com.ujiuye.compare.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.compare.bean.BenchMarking;
import com.ujiuye.compare.service.BenchMarkingService;
import com.ujiuye.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/benchmarking")
public class BenchMarkingController {
    @Autowired
    private BenchMarkingService benchMarkingService;

    @RequestMapping(value = "/amountList/{year}",method = RequestMethod.GET)
    @ResponseBody
    public List<BenchMarking> getAmountList(@PathVariable("year")Integer year){
        List<BenchMarking> list = benchMarkingService.getAmountList(year);
        return list;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getBenchMarkingList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        ModelAndView mv = new ModelAndView("indexvalue-base");
        String requestURI = request.getRequestURI();
        PageInfo<BenchMarking> page = benchMarkingService.getBenchMarkingList(pageNum);
        mv.addObject("requestURI",requestURI);
        mv.addObject("page",page);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(BenchMarking benchMarking){
        benchMarkingService.saveInfo(benchMarking);
        return "redirect:/benchmarking/list";
    }
}
