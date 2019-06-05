package com.how2java.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {//跳转专用
    @RequestMapping("registerPage")
    public String registerPage() {
        return "fore/register";
    }
    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }
    @RequestMapping("loginPage")
    public String loginPage() {
        return "fore/login";
    }
    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }
    @RequestMapping("managerlogin")
    public String managerlogin(){
    return  "admin/managerlogin";
    }

}