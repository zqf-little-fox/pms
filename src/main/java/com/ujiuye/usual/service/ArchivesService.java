package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Archives;

import java.util.Map;

public interface ArchivesService {

    PageInfo<Archives> getArchivesList(Integer pageNum, Map<String, Object> parameterMap);
}
