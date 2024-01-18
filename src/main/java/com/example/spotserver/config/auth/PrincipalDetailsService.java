package com.example.spotserver.config.auth;

import com.example.spotserver.securityStudy.TestUser;
import com.example.spotserver.securityStudy.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class PrincipalDetailsService implements UserDetailsService {

    private TestUserRepository testUserRepository;

    @Autowired
    public PrincipalDetailsService(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TestUser testUser = testUserRepository.findByName(username);
        return new PrincipalDetails(testUser);
    }
}
