package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.global.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_entity_id")
    private MemberEntity writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @Column(name = "content")
    private String content;

    // TODO : 대댓글 컬럼 구현
}
