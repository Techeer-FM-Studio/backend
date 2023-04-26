package com.techeer.fmstudio.domain.banner.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateRequest {

    @NotBlank(message = "Member nickname is required.")
    private String writer;

    @NotNull(message = "Comment is required")
    private String comments;

}
