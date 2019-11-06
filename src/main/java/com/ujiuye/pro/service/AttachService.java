package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;

import java.util.List;

public interface AttachService {
    void saveInfo(Attachment attachment);

    List<Attachment> getAttachList();
}
