package com.ujiuye.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.bean.CustomerExample;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> Test(){
        PageHelper.startPage(2,5);
        EmployeeExample example = new EmployeeExample();
        List<Employee> list = employeeMapper.selectByExample(example);
        PageInfo<Employee> pageInfo = new PageInfo(list);

        System.out.println("总页数"+pageInfo.getPages());
        System.out.println("总记录数"+pageInfo.getTotal());

        System.out.println("当前页"+pageInfo.getPageNum());
        System.out.println("上一页"+pageInfo.getPrePage());
        System.out.println("下一页"+pageInfo.getNextPage());

        System.out.println("是否有上一页"+pageInfo.isHasPreviousPage());
        System.out.println("是否有下一页"+pageInfo.isHasNextPage());

        System.out.println("是否为首页"+pageInfo.isIsFirstPage());
        System.out.println("是否为尾页"+pageInfo.isIsLastPage());
        return list;
    }

    @Override
    public List<Employee> getManagers() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    @Override
    public Employee login(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);

        if (employees != null && employees.size() > 0){
            employee = employees.get(0);
            return employee;
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeList() {
        EmployeeExample example = new EmployeeExample();
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    @Override
    public List<Employee> getInfoList(Integer eid) {
        return employeeMapper.getInfoList(eid);
    }

    @Override
    public int saveInfo(Employee employee) {
        employeeMapper.saveInfo(employee);
        return employee.getEid();
    }
}
