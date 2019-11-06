package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.BaoXiao;
import com.ujiuye.usual.service.BaoXiaoService;
import com.ujiuye.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/bx")
public class BaoXiaoController {
    @Autowired
    private BaoXiaoService baoXiaoService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getBaoXiaoList(HttpServletRequest request, HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        //map的key是浏览器传过来的参数的键去掉前缀的部分
        //map的value是浏览器传过来的参数的值
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        //解析浏览器传过来的参数，转换为字符串
        String searchString = MapUtils.parseParameterMapToString(parameterMap);
        Employee employee = (Employee) session.getAttribute("loginUser");
        //获取登陆者的ID
        Integer eid = employee.getEid();
        //查询列表，分页，模糊查询
        PageInfo<BaoXiao> page = baoXiaoService.getBaoXiaoList(eid,pageNum,parameterMap);
        //获取接口路径
        String requestURI = request.getRequestURI();
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        mv.addObject("page",page);
        mv.addObject("searchString", searchString);
        mv.addObject("requestURI",requestURI);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(BaoXiao baoXiao, HttpSession session){
        Employee employee = (Employee) session.getAttribute("loginUser");
        baoXiao.setEmpFk(employee.getEid());
        baoXiaoService.saveInfo(baoXiao);
        return "redirect:/bx/list";
    }
}
