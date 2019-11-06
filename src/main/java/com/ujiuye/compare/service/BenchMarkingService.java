package com.ujiuye.compare.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.compare.bean.BenchMarking;

import java.util.List;
import java.util.Map;

public interface BenchMarkingService {
    void saveInfo(BenchMarking benchMarking);

    PageInfo<BenchMarking> getBenchMarkingList(Integer pageNum);

    List<BenchMarking> getAmountList(Integer year);
}
