package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
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
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "is_opened")
    private boolean isOpened;

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

    @Builder
    public Banner(MemberEntity member, String title, String memo,
                  LocalDateTime startAt, LocalDateTime endAt) {
        this.member = member;
        this.title = title;
        this.memo = memo;
        this.startAt = startAt;
        this.endAt = endAt;
        this.isFinished = false;
        this.isOpened = false;
        this.likeCnt = 0;
        this.commentList = new ArrayList<>();
        this.imageUrl = new ArrayList<>();
    }

    public void makeFinished() {
        this.isFinished = true;
    }

    public void makeOpened() {
        this.isOpened = true;
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
}
