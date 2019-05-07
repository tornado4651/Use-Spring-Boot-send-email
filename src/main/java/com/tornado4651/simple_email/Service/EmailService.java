package com.tornado4651.simple_email.Service;


import com.tornado4651.simple_email.Entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Component
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 发送简单邮件
     * @param email
     */
    public void sendSimpleEmail(Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email.getReceiver());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());

        try{
            mailSender.send(message);
            logger.info("邮件发送成功!");
        }catch (MailException e){
            logger.error("邮件发送失败!",e);
        }
    }

    /**
     * 发送富文本文件
     * @param email
     */
    public void sendHtmlEmail(Email email){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);

            mailSender.send(message);
            logger.info("Html邮件发送成功！");
        }catch (MessagingException e){
            logger.error("Html邮件发送失败！", e);
        }
    }

    /**
     * 发送附件邮件
     * @param email
     * @param file
     */
    public void sendAttachmentEmail(Email email, MultipartFile file){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent());

//            FileSystemResource file = new FileSystemResource(new File(filePath));
//            String fileName = file.getFilename();
            helper.addAttachment(file.getOriginalFilename(), file);

            mailSender.send(message);
            logger.info("附件邮件发送成功！");
        }catch (MessagingException e){
            logger.error("附件邮件发送失败！",e);
        }

    }

    /**
     * 发送静态邮件
     * @param email
     * @param picPath
     */
    public void sendInlineResourceEmail(Email email, String picPath){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent());

            FileSystemResource pic = new FileSystemResource(new File(picPath));
            String picId = pic.getFilename();
            helper.addInline(picId, pic);

            mailSender.send(message);
            logger.info("静态邮件发送成功！");
        }catch (MessagingException e){
            logger.error("静态资源邮件发送失败！", e);
        }
    }
}
