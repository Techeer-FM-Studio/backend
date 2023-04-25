package com.techeer.fmstudio.domain.s3.service;

import com.techeer.fmstudio.s3.util.S3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Manager s3Uploader;


    public String uploadImage(MultipartFile image) throws IOException {
        // Upload Images to S3 Bucket
        return s3Uploader.upload(image, s3Uploader.getDirName());
    }

    public String deleteImage(String filename) {
        // Upload Images to S3 Bucket
        s3Uploader.delete(filename);
        return filename;
    }

    public String deleteAndCreateImage(MultipartFile image,String filename) throws IOException {
        String url = s3Uploader.upload(image, s3Uploader.getDirName());
        s3Uploader.delete(filename);
        return url;
    }
}