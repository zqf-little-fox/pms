package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.mapper.AttachmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachServiceImpl implements AttachService {
    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public void saveInfo(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }

    @Override
    public List<Attachment> getAttachList() {
        AttachmentExample example = new AttachmentExample();
        List<Attachment> attachments = attachmentMapper.selectByExample(example);
        return attachments;
    }
}
