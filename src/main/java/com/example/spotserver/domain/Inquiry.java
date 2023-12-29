package com.example.spotserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Inquiry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String content;

    @OneToMany
    @JoinColumn(name="inquiryId")
    private List<ImageFile> images;

    @OneToMany(mappedBy = "inquiry")
    private List<Comment> commentList;

    @CreationTimestamp
    private LocalDateTime regDate;
}
