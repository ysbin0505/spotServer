package com.example.spotserver.service;

import com.example.spotserver.domain.Location;
import com.example.spotserver.domain.Poster;
import com.example.spotserver.repository.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PosterService {

    private PosterRepository posterRepository;

    @Autowired
    public PosterService(PosterRepository posterRepository) {
        this.posterRepository = posterRepository;
    }

    public Long addPoster(Poster poster) {
        Poster saveInquiry = posterRepository.save(poster);
        return saveInquiry.getId();
    }

    public List<Poster> getLocationPosters(Location location) {
        List<Poster> posters = posterRepository.findAllByLocation(location);
        return posters;
    }

    public Poster getPoster(Long posterId) {
        Poster poster = posterRepository.findById(posterId)
                .orElseThrow(() -> new NoSuchElementException());
        return poster;
    }
}
