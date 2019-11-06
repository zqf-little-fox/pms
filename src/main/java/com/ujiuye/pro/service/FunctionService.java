package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Function;

import java.util.List;

public interface FunctionService {
    void saveInfo(Function function);

    List<Function> getFunctionList();

    Function getFunctionById(Integer id);

    void updateFunction(Function function);

    List<Function> getfuncNameList(Integer id);

    boolean batchDetele(Integer[] ids);
}
