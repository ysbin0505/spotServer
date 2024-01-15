package com.example.spotserver.securityStudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {


    private TestUserRepository testUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public IndexController(TestUserRepository testUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.testUserRepository = testUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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

    @GetMapping("/loginForm")
    public String loginFrom() {
        return "loginForm.html";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm.html";
    }

    @PostMapping("/join")
    public String join(TestUser testUser) {

        // 비밀번호를 암호화를 안한다면 시큐리티로 로그인할 수 없다.
        String rawPwd = testUser.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        testUser.setPassword(encPwd);

        testUser.setRole("user");
        testUserRepository.save(testUser);
        return "redirect:/loginForm";
    }

}
