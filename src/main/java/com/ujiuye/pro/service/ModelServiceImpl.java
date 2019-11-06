package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Model;
import com.ujiuye.pro.bean.ModelExample;
import com.ujiuye.pro.mapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelMapper modelMapper;

    @Override
    public void saveInfo(Model model) {
        modelMapper.insert(model);
    }

    @Override
    public List<Model> getModelList() {
        ModelExample example = new ModelExample();
        List<Model> models = modelMapper.selectByExample(example);
        return models;
    }

    @Override
    public Model getModelById(Integer id) {
        Model model = modelMapper.selectByPrimaryKey(id);
        return model;
    }

    @Override
    public void updateModel(Model model) {
        modelMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        ModelExample example = new ModelExample();
        ModelExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int row = modelMapper.deleteByExample(example);
        return ids.length == row;
    }

    @Override
    public List<Model> getModNameList(Integer pid) {
        /*ModelExample example = new ModelExample();
        ModelExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(pid);
        List<Model> models = modelMapper.selectByExample(example);
        return models;*/
        return modelMapper.getModNameList(pid);
    }
}
