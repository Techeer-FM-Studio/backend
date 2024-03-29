package com.techeer.fmstudio.domain.s3.controller;

import com.techeer.fmstudio.domain.s3.dto.S3DeleteResponse;
import com.techeer.fmstudio.domain.s3.dto.S3Mapper;
import com.techeer.fmstudio.domain.s3.dto.S3UploadResponse;
import com.techeer.fmstudio.domain.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/banners/images")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class S3Controller {

    private final S3Service service;
    private final S3Mapper mapper;

    @PostMapping
    public ResponseEntity<S3UploadResponse> uploadImage(
            @RequestBody MultipartFile image
    ) throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.toUploadResponseDto(service.uploadImage(image)));
    }

    @DeleteMapping
    public ResponseEntity<S3DeleteResponse> deleteImages(
            @RequestParam String filename
    ){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.toDeleteResponseDto(service.deleteImage(filename)));
    }
}
