package com.ujiuye.sys.service;

import com.ujiuye.sys.mapper.RoleSourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleSourcesServiceImpl implements RoleSourcesService {
    @Resource
    private RoleSourcesMapper roleSourcesMapper;

    @Override
    public void saveInfo(int roleid, String ids) {
        String[] sidArr = ids.split(",");
        for (String sid : sidArr) {
            roleSourcesMapper.saveInfo(roleid,Integer.valueOf(sid));
        }
    }
}
