package com.ujiuye.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleSourcesMapper {
    void saveInfo(@Param("rid") int roleid,@Param("sid") Integer sid);
}
