package com.example.spotserver.config.auth;

import com.example.spotserver.securityStudy.TestUser;
import com.example.spotserver.securityStudy.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 .loginProcessingUrl("/login") 했기에
// "/login" 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 가 실행된다.
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private TestUserRepository testUserRepository;

    @Autowired
    public PrincipalDetailsService(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }



    // 이 메서드 반환값이 Authentication 내부에 쏙 UserDeatils가 들어감
    // 시큐리티 세션 = Authentication(내부 UserDetails)
    // 시큐리티 세션 ( 내부 Authentication(내부 UserDetails) )
    // loadUser... 애가 알아서 세션까지 다 넣어
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 파라미터가 username으로 넘어오는데 loginForm에서도 username으로 맞춰서 넘겨줘야함.
        // username이 아닌 name으로 넘기도록 난 해놨으니 매칭되도록 SecurityConfig에서 .usernameParameter("name") 으로 해준거임.

        TestUser testUser = testUserRepository.findByName(username);
        if(testUser != null) {
            return new PrincipalDetails(testUser);
        }

        return null;
    }
}
