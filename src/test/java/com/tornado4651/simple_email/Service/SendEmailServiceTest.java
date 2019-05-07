package com.tornado4651.simple_email.Service;

import com.tornado4651.simple_email.Entity.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendEmailServiceTest {

    @Resource
    EmailService emailService;

    @Test
    public void testSendSimpleEmail(){
        Email email = new Email();
        emailService.sendSimpleEmail(email);
        System.out.println("发送成功");
    }

    @Test
    public void testSendInlineMail(){
        Email email = new Email();
        String rscId = "1234.jpg";
        email.setContent("<html>" +
                            "<body>" +
                                "这是有图片的邮件：<img src=\'cid:" + rscId + "\' >" +
                            "</body>" +
                        "</html>");
        String imgPath = "/Users/tornado4651/Desktop/1234.jpg ";
        emailService.sendInlineResourceEmail(email,imgPath);
    }

//    @Test
//    public void testSendAttachmentMail(){
//        Email email = new Email();
//        String filePath = "/Users/tornado4651/Desktop/基于Spring Boot的邮件发送查看功能.docx";
//        emailService.sendAttachmentEmail(email,filePath);
//    }
}
