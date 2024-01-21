package com.example.spotserver.repository;

import com.example.spotserver.domain.PosterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosterImageRepository extends JpaRepository<PosterImage, Long> {

    List<PosterImage> findByPosterId(Long posterId);

}
