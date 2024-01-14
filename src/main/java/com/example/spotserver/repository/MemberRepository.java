package com.example.spotserver.repository;

import com.example.spotserver.domain.Member;
import com.example.spotserver.domain.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByTypeIsAndSnsId(MemberType type, Long id);


}
