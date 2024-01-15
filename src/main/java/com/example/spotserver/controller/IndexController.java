package com.example.spotserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping({"","/"})
    public String index() {
        System.out.println("IndexController.index");
        return "index.html";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user.html";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin.html";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager.html";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login.html";
    }

    @GetMapping("/join")
    @ResponseBody
    public String join() {
        return "join.html";
    }

    @GetMapping("/joinProc")
    @ResponseBody
    public String joinProc() {
        return "회원가입 완료";
    }


}
