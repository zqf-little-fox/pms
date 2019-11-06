package com.ujiuye.sys.mapper;

import java.util.List;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer eid);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer eid);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeByEid(Integer eid);

    List<Employee> getInfoList(Integer eid);

    void saveInfo(Employee employee);
}