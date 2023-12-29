package com.example.spotserver.service;

import com.example.spotserver.domain.ImageFile;
import com.example.spotserver.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageFileService {

    private ImageFileRepository imageFileRepository;

    @Autowired
    public ImageFileService(ImageFileRepository imageFileRepository) {
        this.imageFileRepository = imageFileRepository;
    }


    public void saveImageList(List<ImageFile> imageList) {
        imageFileRepository.saveAll(imageList);
    }
}
