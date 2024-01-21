package com.example.spotserver.repository;

import com.example.spotserver.domain.Location;
import com.example.spotserver.domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {

    List<Poster> findAllByLocation(Location location);
}
