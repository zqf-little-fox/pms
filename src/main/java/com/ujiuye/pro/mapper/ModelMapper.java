package com.ujiuye.pro.mapper;

import java.util.List;

import com.ujiuye.pro.bean.Model;
import com.ujiuye.pro.bean.ModelExample;
import org.apache.ibatis.annotations.Param;

public interface ModelMapper {
    int countByExample(ModelExample example);

    int deleteByExample(ModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    int insertSelective(Model record);

    List<Model> selectByExample(ModelExample example);

    Model selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Model record, @Param("example") ModelExample example);

    int updateByExample(@Param("record") Model record, @Param("example") ModelExample example);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

    Model getModelById(Integer id);

    List<Model> getModNameList(Integer pid);
}