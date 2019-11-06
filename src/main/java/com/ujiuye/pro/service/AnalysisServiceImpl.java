package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Resource
    private AnalysisMapper analysisMapper;

    @Override
    public void saveInfo(Analysis analysis) {
        analysis.setAddtime(new Date());
        analysis.setUpdatetime(new Date());
        analysisMapper.insertSelective(analysis);
    }

    @Override
    public List<Analysis> getAnalysisList() {
        AnalysisExample example = new AnalysisExample();
        List<Analysis> list = analysisMapper.selectByExample(example);
        return list;
    }

    @Override
    public Analysis getAnalysisById(Integer id) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        return analysis;
    }

    @Override
    public void updateAnal(Analysis analysis) {
        analysis.setUpdatetime(new Date());
        analysisMapper.updateByPrimaryKeySelective(analysis);
    }

    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int row = analysisMapper.deleteByExample(example);
        return ids.length == row;
    }
}
