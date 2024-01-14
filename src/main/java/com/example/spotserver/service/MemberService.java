package com.example.spotserver.service;

import com.example.spotserver.domain.Member;
import com.example.spotserver.domain.MemberType;
import com.example.spotserver.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        Member addMember = memberRepository.save(member);
        return addMember;
    }

    public boolean existKakaoMember(Long snsId) {
        return memberRepository.existsByTypeIsAndSnsId(MemberType.KAKAO, snsId);
    }
}
