package com.example.spotserver.service;

import com.example.spotserver.domain.ImageFile;
import com.example.spotserver.domain.LocationImageFile;
import com.example.spotserver.repository.ImageFileRepository;
import com.example.spotserver.repository.LocationImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageFileService {

    private ImageFileRepository imageFileRepository;
    private LocationImageFileRepository locationImageFileRepository;

    public ImageFileService(ImageFileRepository imageFileRepository, LocationImageFileRepository locationImageFileRepository) {
        this.imageFileRepository = imageFileRepository;
        this.locationImageFileRepository = locationImageFileRepository;
    }

    public void saveInquiryImageList(List<ImageFile> imageList) {
        imageFileRepository.saveAll(imageList);
    }

    public void saveLocationImageList(List<LocationImageFile> imageList) {
        locationImageFileRepository.saveAll(imageList);
    }

    public List<ImageFile> getInquiryImageList(Long inquiryId) {
        List<ImageFile> imageFiles = imageFileRepository.findByInquiryId(inquiryId);
        return imageFiles;
    }
}
