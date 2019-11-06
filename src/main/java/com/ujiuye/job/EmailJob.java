package com.ujiuye.job;

import com.ujiuye.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Email email = (Email) dataMap.get("email");
        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl)dataMap.get("javaMailSenderImpl");
        Scheduler sched = (Scheduler) dataMap.get("scheduler");

        try {
            //邮件对象
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("zqf1481656983@163.com");
            helper.setTo(email.getEname());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(),true);

            //作为附件下载
            FileSystemResource file = new FileSystemResource(new File(email.getPath()));
            String path = email.getPath();
            path = path.substring(path.lastIndexOf("\\")+1);
            helper.addAttachment(path,file);
            //发送邮件
            javaMailSenderImpl.send(mimeMessage);
            sched.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
