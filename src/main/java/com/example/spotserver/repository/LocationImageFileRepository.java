package com.example.spotserver.repository;

import com.example.spotserver.domain.LocationImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationImageFileRepository extends JpaRepository<LocationImageFile, Long> {
}
