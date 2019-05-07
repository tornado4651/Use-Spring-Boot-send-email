package com.tornado4651.simple_email.Controller;

import com.tornado4651.simple_email.Dao.EmailDao;
import com.tornado4651.simple_email.Entity.Email;
import com.tornado4651.simple_email.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MailSystemController {

    @Autowired
    private EmailService emailService;

    /**
     * 返回主页
     */
    @RequestMapping(value = "/user_index.html", method = RequestMethod.GET)
    public String goIndex() {
        return "html/user_index";
    }

    /**
     * 发送简单邮件的控制器
     */
    @RequestMapping(value = "/email_send_Simple.html", method = RequestMethod.GET)
    public String goSimpleEmail() {return "html/email_send_Simple";}
    @RequestMapping(value = "/SendSimpleEmail",method = RequestMethod.POST)
    public String sendSimpleEmail(@RequestParam("receiver") String receiver, String subject, String content){
        Email email = new Email(receiver, subject, content);
        emailService.sendSimpleEmail(email);
        //存入数据库
        EmailDao emailDao = new EmailDao();
        if(emailDao.recEmail(receiver, subject, content)){
            System.out.println("简单邮件已存入！");
        }
        return "html/email_send_Simple";
    }

    /**
     * 发送富文本邮件的控制器
     */
    @RequestMapping(value = "/email_send_Html.html", method = RequestMethod.GET)
    public String goHtmlEmail() {return "html/email_send_Html";}
    @RequestMapping(value = "/SendHtmlEmail",method = RequestMethod.POST)
    public String sendHtmlEmail(String receiver, String subject, String content){
        Email email = new Email(receiver, subject, content);
        emailService.sendHtmlEmail(email);
        //存入数据库
        EmailDao emailDao = new EmailDao();
        if(emailDao.recEmail(receiver, subject, content)){
            System.out.println("富文本邮件已存入！");
        }
        return "html/email_send_Html";
    }

    /**
     * 发送附件邮件的控制器
     */
    @RequestMapping(value = "/email_send_Attachment.html", method = RequestMethod.GET)
    public String goAttachedEmail() {return "html/email_send_Attachment";}
    @RequestMapping(value = "/SendAttachmentEmail",method = RequestMethod.POST)
    public String sendAttachmentEmail(String receiver, String subject, String content, MultipartFile[] files){
        Email email = new Email(receiver, subject, content);
        for (MultipartFile file : files) {
            emailService.sendAttachmentEmail(email ,file);
        }
        //存入数据库
        EmailDao emailDao = new EmailDao();
        if(emailDao.recEmail(receiver, subject, content)){
            System.out.println("附件邮件已存入！");
        }
        return "html/email_send_Attachment";
    }

    /**
     * 收件箱
     */
    @RequestMapping(value = "/email_check.html", method = RequestMethod.GET)
    public String checkEmail(WebRequest webRequest) {
        EmailDao emailDao = new EmailDao();
        List<com.tornado4651.simple_email.Entity.Email> datalist;
               datalist = emailDao.getEmail();

        int num = datalist.size();
//        邮件数据列表
        webRequest.setAttribute("list", datalist, RequestAttributes.SCOPE_REQUEST);
//        邮件数量
        webRequest.setAttribute("num", num, RequestAttributes.SCOPE_REQUEST);

        return "html/email_check";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logOut() {return "login";}
}
