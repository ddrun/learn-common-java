package com.example.djran.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018/4/13
 *
 * @author dengjr
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/login")
    public  String login(){
        return "login";
    }

    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg","登陆失败，用户名或者密码错误！");
        return "login";
    }
    @RequestMapping("/403")
    public String accesssDenied() {
        return "403";
    }
}
