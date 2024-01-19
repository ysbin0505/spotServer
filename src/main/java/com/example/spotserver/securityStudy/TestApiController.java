package com.example.spotserver.securityStudy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TestUserRepository testUserRepository;


    @Autowired
    public TestApiController(BCryptPasswordEncoder bCryptPasswordEncoder, TestUserRepository testUserRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.testUserRepository = testUserRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "<h1>home</h1>";
    }

    @PostMapping("/token")
    public String token() {
        return "<h1>token</h1>";
    }

    @PostMapping("/join")
    public String join(@RequestBody TestUser testUser) {
        testUser.setPassword(bCryptPasswordEncoder.encode(testUser.getPassword()));
        testUser.setRole("user");
        testUserRepository.save(testUser);
        return "회원가입 완료";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
