package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.BaoXiao;
import com.ujiuye.usual.bean.BaoXiaoExample;
import com.ujiuye.usual.mapper.BaoXiaoMapper;
import com.ujiuye.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaoXiaoServiceImpl implements BaoXiaoService {
    @Resource
    private BaoXiaoMapper baoXiaoMapper;

    @Override
    public void saveInfo(BaoXiao baoXiao) {
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        baoXiao.setBxid(prefix);
        baoXiao.setBxstatus(0);
        baoXiaoMapper.insert(baoXiao);
    }

    @Override
    public PageInfo<BaoXiao> getBaoXiaoList(Integer eid, Integer pageNum, Map<String, Object> parameterMap) {
        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        Map<String,String> mybatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        String status = mybatisMap.get("status");
        if (status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.valueOf(status));
        }
        String keyword = mybatisMap.get("keyword");
        if (keyword != null && keyword != ""){
            criteria.andBxremarkLike(keyword);
        }
        PageHelper.startPage(pageNum,3);
        List<BaoXiao> baoXiaos = baoXiaoMapper.selectByExample(example);
        PageInfo<BaoXiao> page = new PageInfo<BaoXiao>(baoXiaos,5);
        return page;
    }
}
