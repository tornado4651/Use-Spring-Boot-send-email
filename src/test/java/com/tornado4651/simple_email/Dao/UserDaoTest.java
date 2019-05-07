package com.tornado4651.simple_email.Dao;

import com.tornado4651.simple_email.Domain.Email;
import com.tornado4651.simple_email.Entity.User;
import com.tornado4651.simple_email.Service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Resource
    UserDao userDao;

    @Test
    public void testUserDao(){

        User user = new User();
//        emailService.sendSimpleEmail(email);
    }

}
