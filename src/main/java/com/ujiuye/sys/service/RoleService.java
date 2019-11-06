package com.ujiuye.sys.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    int saveInfo(Role role);

    PageInfo<Role> getRoleList(Integer pageNum, Map<String, Object> parameterMap);

    Role getRoleById(Integer roleid);

    List<Role> getRoleJsonList();
}
