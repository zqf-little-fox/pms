package com.ujiuye.pro.mapper;

import java.util.List;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.sys.bean.Employee;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    Project getProjectById(Integer pid);

    List<Project> proInList();

    List<Project> getProjectwithSearch(@Param("cid") Integer cid, @Param("keyword") String keyword, @Param("orderby") Integer orderby);

    List<Project> proNotInList();

    List<Project> getProjectList();

    List<Project> jsonFileList();
}