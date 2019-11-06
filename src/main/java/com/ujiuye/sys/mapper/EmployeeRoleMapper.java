package com.ujiuye.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface EmployeeRoleMapper {
    void insert(@Param("eid") int eid, @Param("rid")Integer rid);
}
