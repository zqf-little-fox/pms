package com.ujiuye.info.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.info.bean.Email;
import com.ujiuye.info.bean.EmailExample;
import com.ujiuye.info.mapper.EmailMapper;
import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.mapper.ArchivesMapper;
import com.ujiuye.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private EmailMapper emailMapper;
    @Resource
    private ArchivesMapper archivesMapper;

    @Override
    public void saveInfo(Email email) {
        emailMapper.insert(email);
    }

    @Override
    public PageInfo<Email> getEmailList(Integer eid, Integer pageNum, Map<String, Object> parameterMap) {
        EmailExample example = new EmailExample();
        EmailExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        Map<String, String> mybatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        String type = mybatisMap.get("type");
        String keyword = mybatisMap.get("keyword");
        if (keyword != null && keyword != "") {
            if (type != null && type != "") {
                if (type == "0") {
                    criteria.andTitleLike(keyword);
                } else if (type == "1") {
                    criteria.andContentLike(keyword);
                }
            } else {
                criteria.andTitleLike(keyword);
                EmailExample.Criteria criteria1 = example.createCriteria();
                criteria1.andContentLike(keyword);
                example.or(criteria1);
            }
        }
        String orderby = mybatisMap.get("orderby");
        if (orderby != null && orderby != ""){
            if (orderby == "0"){
                example.setOrderByClause("sendtime desc");
            }
        }
        PageHelper.startPage(pageNum,5);
        List<Email> emails = emailMapper.selectByExample(example);
        PageInfo<Email> page = new PageInfo<>(emails,5);
        return page;
    }

    @Override
    public void deleteEmailById(Integer id) {
        emailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Email> getGiveMailList(Integer eid, Map<String, Object> parameterMap, Integer pageNum) {
        Archives archives = archivesMapper.getEmailById(eid);
        String email = archives.getEmail();
        EmailExample example = new EmailExample();
        EmailExample.Criteria criteria = example.createCriteria();
        criteria.andEnameEqualTo(email);
        Map<String, String> myBatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        String type = myBatisMap.get("type");
        String keyword = myBatisMap.get("keyword");
        if (keyword != null && keyword != "") {
            if (type != null && type != "") {
                if (type == "0") {
                    criteria.andTitleLike(keyword);
                } else if (type == "1") {
                    criteria.andContentLike(keyword);
                }
            } else {
                criteria.andTitleLike(keyword);
                EmailExample.Criteria criteria1 = example.createCriteria();
                criteria1.andContentLike(keyword);
                example.or(criteria1);
            }
        }
        String orderby = myBatisMap.get("orderby");
        if (orderby != null && orderby != ""){
            if (orderby == "0"){
                example.setOrderByClause("sendtime desc");
            }
        }
        PageHelper.startPage(pageNum,5);
        List<Email> emails = emailMapper.selectByExample(example);
        PageInfo<Email> page = new PageInfo<>(emails,5);
        return page;
    }
}
