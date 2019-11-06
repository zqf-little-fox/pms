package com.ujiuye.sys.service;

import com.ujiuye.sys.mapper.EmployeeRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
    @Resource
    private EmployeeRoleMapper employeeRoleMapper;

    @Override
    public void insert(int eid, String[] roleids) {
        for (String roleid : roleids) {
            employeeRoleMapper.insert(eid,Integer.valueOf(roleid));
        }
    }
}
