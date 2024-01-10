package com.example.spotserver.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageStore {

    @Value("${inquiryImg.dir}")
    private String inquiryImgDir;

    @Value("${locationImg.dir}")
    private String locationImgDir;



    public List<ImageFile> storeInquiryImages(List<MultipartFile> images) throws IOException {
        List<ImageFile> result = new ArrayList<>();

        for (MultipartFile image : images) {
            String uploadFileName = image.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();
            int pos = uploadFileName.indexOf(".");
            String ext = uploadFileName.substring(pos + 1);

            String storeFileName = uuid + "." + ext;

            image.transferTo(new File(getInquiryImgFullPath(storeFileName)));
            result.add(new ImageFile(uploadFileName, storeFileName));
        }
        return result;
    }

    public List<LocationImageFile> storeLocationImages(List<MultipartFile> images) throws IOException {
        List<LocationImageFile> result = new ArrayList<>();

        for (MultipartFile image : images) {
            String uploadFileName = image.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();
            int pos = uploadFileName.indexOf(".");
            String ext = uploadFileName.substring(pos + 1);

            String storeFileName = uuid + "." + ext;

            image.transferTo(new File(getLocationImgFullPath(storeFileName)));
            result.add(new LocationImageFile(uploadFileName, storeFileName));
        }
        return result;
    }



    public String getLocationImgFullPath(String imageFilName) {
        return locationImgDir+imageFilName;
    }

    public String getInquiryImgFullPath(String imageFileName) {
        return inquiryImgDir+imageFileName;
    }
}
