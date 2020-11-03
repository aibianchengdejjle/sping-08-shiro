package com.jjl.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
    @RequestMapping("/test")
    public String test(){
        return "index";
    }
    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }
    @RequestMapping("/toLogin")
    public String tologin(){
        return "login";
    }


    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登陆的方法
        try {
            //密码校验成功进入主页
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名异常");
            return "login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码异常");
            return "login";
        }
    }
    @ResponseBody
    @RequestMapping("/noauthc")
    public String no(){
        return "未经授权无法访问";
    }

    //实现注销
    @RequestMapping("/Logout")
    public String logout(){
        //完成对session的注销
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.removeAttribute("user");
        return "redirect:/toLogin";
    }

}
