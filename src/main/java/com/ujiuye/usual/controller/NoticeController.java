package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import com.ujiuye.utils.MapUtils;
import com.ujiuye.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/edit/{nid}",method = RequestMethod.GET)
    public String editNoticeByNid(@PathVariable("nid")Integer nid,Map<String,Object> map){
        Notice notice = noticeService.getNoticeByNid(nid);
        map.put("notice",notice);
        return "notice-send";
    }

    @RequestMapping(value = "/del/{nid}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity deleteNoticeByNid(@PathVariable("nid")Integer nid){
        noticeService.deleteNoticeByNid(nid);
        return ResultEntity.success();
    }

    @RequestMapping(value = "/info/{nid}",method = RequestMethod.GET)
    @ResponseBody
    public Notice getNoticeByNid(@PathVariable("nid")Integer nid){
        Notice notice = noticeService.getNoticeByNid(nid);
        return notice;
    }

    @RequestMapping(value = "/latestList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLatestNoticeList(){
        List<Notice> notices = noticeService.getLatestNoticeList();
        return ResultEntity.success().put("notices",notices);
    }

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNoticeList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        //map的key是浏览器传过来的参数的键去掉前缀的部分
        //map的value是浏览器传过来的参数的值
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        //解析浏览器传过来的参数，转换为字符串
        String searchString = MapUtils.parseParameterMapToString(parameterMap);
        //查询列表，分页，模糊查询
        PageInfo<Notice> page = noticeService.getNoticeList(pageNum,parameterMap);
        //获取接口路径
        String requestURI = request.getRequestURI();
        return ResultEntity.success().put("page",page).put("searchString",searchString).put("requestURI",requestURI);
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }
}
