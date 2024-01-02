package com.example.spotserver.repository;

import com.example.spotserver.domain.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {

    List<ImageFile> findByInquiryId(Long inquiryId);

}
