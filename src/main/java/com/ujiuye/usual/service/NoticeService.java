package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    void saveInfo(Notice notice);

    PageInfo<Notice> getNoticeList(Integer pageNum, Map<String, Object> parameterMap);

    List<Notice> getLatestNoticeList();

    Notice getNoticeByNid(Integer nid);

    void deleteNoticeByNid(Integer nid);
}
