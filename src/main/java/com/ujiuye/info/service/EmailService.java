package com.ujiuye.info.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.info.bean.Email;

import java.util.Map;

public interface EmailService {
    void saveInfo(Email email);

    PageInfo<Email> getEmailList(Integer eid, Integer pageNum, Map<String, Object> parameterMap);

    void deleteEmailById(Integer id);

    PageInfo<Email> getGiveMailList(Integer eid, Map<String, Object> parameterMap, Integer pageNum);
}
