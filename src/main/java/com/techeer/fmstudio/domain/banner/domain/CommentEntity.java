package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.global.BaseEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_entity_id")
    private MemberEntity writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id")
    private BannerEntity banner;

    @Column(name = "contents")
    private String contents;

    // TODO : 대댓글 컬럼 구현

    @Builder
    public CommentEntity(MemberEntity writer, BannerEntity banner, String contents){
        this.writer = writer;
        this.banner = banner;
        this.contents = contents;
    }
}
