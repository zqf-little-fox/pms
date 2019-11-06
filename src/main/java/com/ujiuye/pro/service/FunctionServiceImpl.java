package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionExample;
import com.ujiuye.pro.mapper.FunctionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Resource
    private FunctionMapper functionMapper;

    @Override
    public void saveInfo(Function function) {
        functionMapper.insert(function);
    }

    @Override
    public List<Function> getFunctionList() {
        FunctionExample example = new FunctionExample();
        List<Function> functions = functionMapper.selectByExample(example);
        return functions;
    }

    @Override
    public Function getFunctionById(Integer id) {
        Function function = functionMapper.selectByPrimaryKey(id);
        return function;
    }

    @Override
    public void updateFunction(Function function) {
        functionMapper.updateByPrimaryKeySelective(function);
    }

    @Override
    public List<Function> getfuncNameList(Integer id) {
        /*FunctionExample example = new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andModeleFkEqualTo(id);
        List<Function> functions = functionMapper.selectByExample(example);
        return functions;*/
        return functionMapper.getfuncNameList(id);
    }

    @Override
    public boolean batchDetele(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        FunctionExample example = new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int row = functionMapper.deleteByExample(example);
        return ids.length == row;
    }
}
