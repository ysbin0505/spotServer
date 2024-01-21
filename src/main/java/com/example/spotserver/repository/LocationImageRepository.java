package com.example.spotserver.repository;

import com.example.spotserver.domain.Location;
import com.example.spotserver.domain.LocationImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocationImageRepository extends JpaRepository<LocationImage, Long> {

    List<LocationImage> findByLocationId(Long locationId);
}
