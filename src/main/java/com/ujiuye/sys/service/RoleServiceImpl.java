package com.ujiuye.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.bean.RoleExample;
import com.ujiuye.sys.mapper.RoleMapper;
import com.ujiuye.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int saveInfo(Role role) {
        roleMapper.saveInfo(role);
        return role.getRoleid();
    }

    @Override
    public PageInfo<Role> getRoleList(Integer pageNum, Map<String, Object> parameterMap) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        Map<String, String> myBatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        PageHelper.startPage(pageNum,5);
        List<Role> roles = roleMapper.selectByExample(example);
        PageInfo<Role> page = new PageInfo<>(roles,5);
        return page;
    }

    @Override
    public Role getRoleById(Integer roleid) {
        Role role = roleMapper.selectByPrimaryKey(roleid);
        return role;
    }

    @Override
    public List<Role> getRoleJsonList() {
        RoleExample example = new RoleExample();
        return roleMapper.selectByExample(example);
    }
}
