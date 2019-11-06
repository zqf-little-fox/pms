package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.BaoXiao;

import java.util.List;
import java.util.Map;

public interface BaoXiaoService {
    void saveInfo(BaoXiao baoXiao);

    PageInfo<BaoXiao> getBaoXiaoList(Integer eid, Integer pageNum,Map<String, Object> parameterMap);
}
