package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.PostTie;
import com.ujiuye.usual.mapper.PostTieMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostTieServiceImpl implements PostTieService {
    @Resource
    private PostTieMapper postTieMapper;

    @Override
    public void saveInfo(PostTie postTie) {
        postTieMapper.saveInfo(postTie);
    }
}
