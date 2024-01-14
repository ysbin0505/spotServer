package com.example.spotserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @CreationTimestamp
    private LocalDateTime regDate;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private Long snsId;

}
