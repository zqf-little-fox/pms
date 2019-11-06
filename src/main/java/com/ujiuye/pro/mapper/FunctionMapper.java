package com.ujiuye.pro.mapper;


import java.util.List;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionExample;
import org.apache.ibatis.annotations.Param;

public interface FunctionMapper {
    int countByExample(FunctionExample example);

    int deleteByExample(FunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionExample example);

    Function selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    Function getFunctionById(Integer id);

    List<Function> getfuncNameList(Integer id);
}