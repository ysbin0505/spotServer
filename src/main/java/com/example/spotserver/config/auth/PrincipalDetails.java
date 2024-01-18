package com.example.spotserver.config.auth;

import com.example.spotserver.securityStudy.TestUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private TestUser testUser;

    public PrincipalDetails(TestUser testUser) {
        this.testUser = testUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return testUser.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return testUser.getPassword();
    }

    @Override
    public String getUsername() {
        return testUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public TestUser getTestUser() {
        return testUser;
    }
}
