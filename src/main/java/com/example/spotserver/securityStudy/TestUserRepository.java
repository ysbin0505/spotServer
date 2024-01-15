package com.example.spotserver.securityStudy;

import org.springframework.data.jpa.repository.JpaRepository;


// @Repository 어노테이션이 없어도 된다. JpaRepository를 상속 받았기 떄문
public interface TestUserRepository extends JpaRepository<TestUser, Long> {

}
