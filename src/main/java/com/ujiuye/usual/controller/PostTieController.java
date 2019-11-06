package com.ujiuye.usual.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.PostTie;
import com.ujiuye.usual.service.PostTieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/tz")
public class PostTieController {
    @Autowired
    private PostTieService postService;

    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(PostTie postTie, HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        postTie.setEmpFk(eid);
        postTie.setAddtime(new Date());
        postService.saveInfo(postTie);
        return "redirect:/main.jsp";
    }
}
