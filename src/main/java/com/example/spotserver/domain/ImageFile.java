package com.example.spotserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ImageFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /*
    일대다 단방향은 주의하자.
    보통 키의 관리는 다쪽에서 한다.
    그러나 Member에는 외래 키를 매핑할 수 있는 참조 필드가 없다.
    Team의 members 필드에 외래 키를 매핑할 수 있는 참조 필드가 있다.
    그래서 @JoinColumn의 name 속성의 값을 Member 필드의 외래 키로 작성한다.
    */
    private Long inquiryId;

    private String uploadFileName; // 업로드한 파일명
    private String storeFileName; // 시스템에 저장한 파일명


    public ImageFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
