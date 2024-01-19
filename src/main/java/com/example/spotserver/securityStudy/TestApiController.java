package com.example.spotserver.securityStudy;


import com.example.spotserver.config.auth.PrincipalDetails;
import com.example.spotserver.domain.Member;
import com.example.spotserver.service.InquiryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TestUserRepository testUserRepository;
    private InquiryService inquiryService;


    @Autowired
    public TestApiController(BCryptPasswordEncoder bCryptPasswordEncoder, TestUserRepository testUserRepository, InquiryService inquiryService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.testUserRepository = testUserRepository;
        this.inquiryService = inquiryService;
    }

    @GetMapping("/home")
    public String home() {
        return "<h1>home</h1>";
    }

    @PostMapping("/join")
    public String join(@RequestBody TestUser testUser) {
        testUser.setPassword(bCryptPasswordEncoder.encode(testUser.getPassword()));
        testUser.setRole("user");
        testUserRepository.save(testUser);
        return "회원가입 완료";
    }

    @GetMapping("/write1")
    public String write1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member member = principal.getMember();
        System.out.println("[1] member = " + member);
        return "ok";
    }

    @GetMapping("/write2")
    public String write2(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Member member = principalDetails.getMember();
        System.out.println("[2] member = " + member);
        return "ok";
    }

    @GetMapping("/write3")
    public String write3(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member) {
        if(member==null) {
            System.out.println("널이유");
        } else {
            System.out.println("[3] member = " + member);
        }

        return "ok";
    }

    @GetMapping("/user/test")
    public String userTest() {
        return "ookk";
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
