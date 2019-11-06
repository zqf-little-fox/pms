package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.EmployeeRoleService;
import com.ujiuye.sys.service.EmployeeService;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRoleService employeeRoleService;
    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee,String[] roleids){
        int eid = employeeService.saveInfo(employee);
        employeeRoleService.insert(eid,roleids);
        return "redirect:/user.jsp";
    }


    @RequestMapping(value = "/others",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getInfoList(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Employee> list = employeeService.getInfoList(eid);
        return list;
    }

    @RequestMapping(value = "/empNameList",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> empNameList(){
        return employeeService.getEmployeeList();
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String validateCode = (String)session.getAttribute("validateCode");
        if (code.equalsIgnoreCase(validateCode)){
            session.removeAttribute("validateCode");
            employee = employeeService.login(employee);
            if (employee != null){
                Integer eid = employee.getEid();
                List<Sources> sourceslist = sourcesService.getSourcesByEid(eid);
                session.setAttribute("sourceslist",sourceslist);
                session.setAttribute("loginUser",employee);
                return "redirect:/index.jsp";
            }else {
                attributes.addFlashAttribute("msg","用户名或密码错误，请重新输入！");
                return "redirect:/login";
            }
        }
        attributes.addFlashAttribute("msg","验证码错误，请重新输入！");
        return "redirect:/login";
    }

    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers(){
        return employeeService.getManagers();
    }
}
