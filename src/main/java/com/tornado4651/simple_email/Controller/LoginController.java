package com.tornado4651.simple_email.Controller;

import com.tornado4651.simple_email.Dao.UserDao;
import com.tornado4651.simple_email.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(String username, String password, String post){
        if("post".equals(post)){
            User user = null;
            user = userDao.get_ByUsername(username);
            if (user!=null && password.equals(user.getPassword())){
                return "html/user_index";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(String username, String password)
    {
        if(userDao.registerUser(username, password)){
            System.out.println("注册成功！");
        }
        return "login";
    }


    @RequestMapping(value = "/ask",method = RequestMethod.GET)
    public String askPassword(){
        return "myPassword";
    }
}
