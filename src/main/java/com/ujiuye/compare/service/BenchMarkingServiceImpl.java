package com.ujiuye.compare.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.compare.bean.BenchMarking;
import com.ujiuye.compare.mapper.BenchMarkingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BenchMarkingServiceImpl implements BenchMarkingService {
    @Resource
    private BenchMarkingMapper benchMarkingMapper;

    @Override
    public void saveInfo(BenchMarking benchMarking) {
        benchMarking.setCreateTime(new Date());
        benchMarkingMapper.saveInfo(benchMarking);
    }

    @Override
    public PageInfo<BenchMarking> getBenchMarkingList(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<BenchMarking> list = benchMarkingMapper.getBenchMarkingList();
        PageInfo<BenchMarking> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<BenchMarking> getAmountList(Integer year) {
        return benchMarkingMapper.getAmountList(year);
    }
}
