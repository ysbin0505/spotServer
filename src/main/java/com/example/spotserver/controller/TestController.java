package com.example.spotserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public Map test() {
        System.out.println("누가 요청했어");
        Map<String, Object> m = new HashMap<>();
        m.put("name", "LSM");
        m.put("age", 10);
        return m;
    }
}
