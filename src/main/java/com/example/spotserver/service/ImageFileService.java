package com.example.spotserver.service;

import com.example.spotserver.domain.PosterImage;
import com.example.spotserver.domain.LocationImage;
import com.example.spotserver.repository.PosterImageRepository;
import com.example.spotserver.repository.LocationImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageFileService {

    private PosterImageRepository posterImageRepository;
    private LocationImageRepository locationImageRepository;

    public ImageFileService(PosterImageRepository posterImageRepository, LocationImageRepository locationImageRepository) {
        this.posterImageRepository = posterImageRepository;
        this.locationImageRepository = locationImageRepository;
    }

    public void savePosterImageList(List<PosterImage> imageList) {
        posterImageRepository.saveAll(imageList);
    }

    public void saveLocationImageList(List<LocationImage> imageList) {
        locationImageRepository.saveAll(imageList);
    }

    public List<PosterImage> getPosterImageList(Long posterId) {
        List<PosterImage> posterImages = posterImageRepository.findByPosterId(posterId);
        return posterImages;
    }

    public List<LocationImage> getLocationImageList(Long locationId) {
        List<LocationImage> locationImages = locationImageRepository.findByLocationId(locationId);
        return locationImages;
    }

    public LocationImage getLocationImage(Long locationImageId) {
        LocationImage locationImage = locationImageRepository.findById(locationImageId)
                .orElseThrow(() -> new NoSuchElementException());
        return locationImage;
    }

    public PosterImage getPosterImage(Long posterImageId) {
        Optional<PosterImage> posterImage = posterImageRepository.findById(posterImageId);
        return posterImage.get();
    }
}
