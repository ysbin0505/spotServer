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



    // 해당 유저의 권한을 리턴하는 곳.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 현재 TestUser의 권한은 String이라 타입을 맞춰줘야함
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
        // 계정이 만료되었는지 여부를 판단하는 로직을 구현
        // 만료되었으면 false, 그렇지 않으면 true 반환
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 안잠겼니?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //isCredentialsNonExpired() 메소드는 계정의 자격 증명이 만료되었으면 false를 반환하며, 만료되지 않았으면 true를 반환합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화 되어 있니?
        // 이런것들 언제 false 하느냐? User 필드의 TimeStamp를 통해 휴면 계정을 관리하여
        // 여기에 getLoginDate를 가져와서 현재시간 - 로그인 시간이 1년을 넘으면 false로 반환해버리면 된다!
        return true;
    }
}
