package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Model;

import java.util.List;

public interface ModelService {
    void saveInfo(Model model);

    List<Model> getModelList();

    Model getModelById(Integer id);

    void updateModel(Model model);

    boolean batchDelete(Integer[] ids);

    List<Model> getModNameList(Integer pid);
}
