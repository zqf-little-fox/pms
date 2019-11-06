package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @RequestMapping(value =  "/list", method = RequestMethod.GET)
    public ModelAndView getAttachList(){
        ModelAndView mv = new ModelAndView("project-file");
        List<Attachment> list = attachService.getAttachList();
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Attachment attachment, MultipartFile multipartFile, HttpSession session){
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
        attachment.setPath(realPath+"\\"+realName);
        attachService.saveInfo(attachment);
        return "redirect:/attach/list";
    }
}
