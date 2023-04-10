package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    private Long id;

//    private MemberEntity writer;

    private String comment;

    // 이후에 대댓글 컬럼 추가할 예정
}
