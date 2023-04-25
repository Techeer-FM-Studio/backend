package com.techeer.fmstudio.domain.s3.dto;

import org.springframework.stereotype.Component;

@Component
public class S3Mapper {

    public S3UploadResponse toUploadResponseDto(String url){
        return S3UploadResponse.builder()
                .url(url)
                .build();
    }

    public S3DeleteResponse toDeleteResponseDto(String filename){
        return S3DeleteResponse.builder()
                .filename(filename)
                .build();
    }
}
