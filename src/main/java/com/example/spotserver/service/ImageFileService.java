package com.example.spotserver.service;

import com.example.spotserver.domain.PosterImage;
import com.example.spotserver.domain.LocationImage;
import com.example.spotserver.repository.PosterImageRepository;
import com.example.spotserver.repository.LocationImageFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageFileService {

    private PosterImageRepository posterImageRepository;
    private LocationImageFileRepository locationImageFileRepository;

    public ImageFileService(PosterImageRepository posterImageRepository, LocationImageFileRepository locationImageFileRepository) {
        this.posterImageRepository = posterImageRepository;
        this.locationImageFileRepository = locationImageFileRepository;
    }

    public void savePosterImageList(List<PosterImage> imageList) {
        posterImageRepository.saveAll(imageList);
    }

    public void saveLocationImageList(List<LocationImage> imageList) {
        locationImageFileRepository.saveAll(imageList);
    }

    public List<PosterImage> getPosterImageList(Long posterId) {
        List<PosterImage> imageFiles = posterImageRepository.findByPosterId(posterId);
        return imageFiles;
    }
}
