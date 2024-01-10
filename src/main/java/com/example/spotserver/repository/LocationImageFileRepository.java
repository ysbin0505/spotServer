package com.example.spotserver.repository;

import com.example.spotserver.domain.LocationImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationImageFileRepository extends JpaRepository<LocationImage, Long> {
}
