package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "banner")
public class BannerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "banner_type")
    private BannerType bannerType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "memo")
    private String memo;

    @NotNull
    @Column(name = "start_at")
    private LocalDateTime startAt;

    @NotNull
    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "is_finished")
    private boolean isFinished;

    @OneToMany(mappedBy = "banner" , fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comment> commentList;

    @Column(name = "image_url")
    @ElementCollection
    @CollectionTable(name = "image_url",
     joinColumns = @JoinColumn(name = "banner_id"))
    private List<String> imageUrl;

    @Column(name = "like_count")
    private Integer likeCnt;

    @Column(name = "read_count")
    private Integer readCnt;

    @Builder
    public BannerEntity(BannerType bannerType, MemberEntity member, String title, String memo,
                        LocalDateTime startAt, LocalDateTime endAt) {
        this.bannerType = bannerType;
        this.member = member;
        this.title = title;
        this.memo = memo;
        this.startAt = startAt;
        this.endAt = endAt;
        this.isFinished = false;
        this.likeCnt = 0;
        this.commentList = new ArrayList<>();
        this.imageUrl = new ArrayList<>();
        this.readCnt = 0;
    }

    public void makeFinished() {
        this.isFinished = true;
    }

    public void addImageUrl(List<String> imageUrl) {
        this.imageUrl.addAll(imageUrl);
    }

    public void addComment(List<Comment> comments) {
        this.commentList.addAll(comments);
    }

    public void update(String title, String memo,
                       LocalDateTime startAt, LocalDateTime endAt) {
        this.title = title;
        this.memo = memo;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void deleteBanner(){
        this.delete();
    }
}
