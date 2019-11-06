package com.ujiuye.info.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.info.bean.Email;
import com.ujiuye.info.service.EmailService;
import com.ujiuye.job.EmailJob;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.utils.MapUtils;
import com.ujiuye.utils.ResultEntity;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @RequestMapping(value = "/giveList",method = RequestMethod.GET)
    public ModelAndView getGiveMailList(HttpSession session,HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        String requestURI = request.getRequestURI();
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String searchString = MapUtils.parseParameterMapToString(parameterMap);
        PageInfo<Email> page = emailService.getGiveMailList(eid,parameterMap,pageNum);
        ModelAndView mv = new ModelAndView("message-give");
        mv.addObject("requestURI",requestURI);
        mv.addObject("searchString",searchString);
        mv.addObject("page",page);
        return mv;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity deleteEmailById(@PathVariable("id")Integer id){
        emailService.deleteEmailById(id);
        return ResultEntity.success();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getEmailList(HttpSession session,HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        String requestURI = request.getRequestURI();
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String searchString = MapUtils.parseParameterMapToString(parameterMap);
        PageInfo<Email> page = emailService.getEmailList(eid,pageNum,parameterMap);
        ModelAndView mv = new ModelAndView("message");
        mv.addObject("page",page);
        mv.addObject("requestURI",requestURI);
        mv.addObject("searchString",searchString);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(HttpSession session, Email email, MultipartFile multipartFile) throws Exception {
        //发件人id
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        email.setEmpFk(eid);

        //文件上传
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String realName = UUID.randomUUID().toString().replaceAll("-", "")+originalFilename;
        try {
            multipartFile.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        email.setPath(realPath+"\\"+realName);
        emailService.saveInfo(email);
        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("javaMailSenderImpl",javaMailSender);

        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().
                withIdentity("trigger1", "group1").
                withSchedule(SimpleScheduleBuilder.simpleSchedule()).
                startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        jobDataMap.put("scheduler",sched);
        //为sched对象新增job以及对于的simpleTrigger
        sched.scheduleJob(job,trigger);
        //启动定时任务管理器
        sched.start();
        return "redirect:/message.jsp";
    }
}
