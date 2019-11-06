package com.ujiuye.sys.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.service.RoleService;
import com.ujiuye.sys.service.RoleSourcesService;
import com.ujiuye.utils.MapUtils;
import com.ujiuye.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleSourcesService roleSourcesService;

    @RequestMapping(value = "/roleList",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoleJsonList(){
        return roleService.getRoleJsonList();
    }

    @RequestMapping(value = "/detail/{roleid}",method = RequestMethod.GET)
    public String getRoleById(@PathVariable("roleid")Integer roleid,Map<String,Object> map){
        Role role = roleService.getRoleById(roleid);
        map.put("role",role);
        return "role-look";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getRoleList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        String requestURI = request.getRequestURI();
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String searchString = MapUtils.parseParameterMapToString(parameterMap);
        PageInfo<Role> page = roleService.getRoleList(pageNum,parameterMap);
        ModelAndView mv = new ModelAndView("role");
        mv.addObject("requestURI",requestURI);
        mv.addObject("searchString",searchString);
        mv.addObject("page",page);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){
        int roleid = roleService.saveInfo(role);
        roleSourcesService.saveInfo(roleid,ids);
        return ResultEntity.success();
    }
}
