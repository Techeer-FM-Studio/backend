package com.techeer.fmstudio.domain.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class S3DeleteResponse {

    @NotNull
    private String filename;

}