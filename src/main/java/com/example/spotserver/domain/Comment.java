package com.example.spotserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private Poster poster;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String content;

    @CreationTimestamp
    private LocalDateTime regDate;
}
