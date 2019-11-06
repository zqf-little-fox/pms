package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourcesById(int i);

    void saveInfo(Sources sources);

    Sources getSourcesInfo(Integer id);

    void updateInfo(Sources sources);

    void deleteSources(Integer id);

    List<Sources> getSourcesByEid(Integer eid);
}
