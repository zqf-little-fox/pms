package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.bean.ArchivesExample;
import com.ujiuye.usual.mapper.ArchivesMapper;
import com.ujiuye.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Resource
    private ArchivesMapper archivesMapper;

    @Override
    public PageInfo<Archives> getArchivesList(Integer pageNum, Map<String, Object> parameterMap) {
        ArchivesExample example = new ArchivesExample();
        ArchivesExample.Criteria criteria = example.createCriteria();
        Map<String, String> myBatisMap = MapUtils.parseParameterMapToMyBatisMap(parameterMap);
        PageHelper.startPage(pageNum,5);
        List<Archives> archives = archivesMapper.selectByExample(example);
        PageInfo<Archives> page = new PageInfo<>(archives,5);
        return page;
    }
}
