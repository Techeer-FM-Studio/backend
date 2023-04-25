package com.techeer.fmstudio.domain.member.domain;

import com.techeer.fmstudio.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotBlank
    @Column(name = "login_id")
    private String loginId;

    @NotBlank
    @Column(name = "login_password")
    private String loginPassword;

    @NotBlank
    @Column(name = "nickname", unique=true)
    private String nickname;

    @Column(name = "member_status")
    private MemberStatus memberStatus;

    @NotNull
    @Column(name = "introduction")
    private String introduction;

    @ElementCollection
    @CollectionTable(name = "interests",
        joinColumns = @JoinColumn(name = "member_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "interests")
    private List<MemberInterest> interestList;

    @Builder
    public MemberEntity(String loginId, String loginPassword, String nickname, String introduction,
                        List<MemberInterest> interestList, MemberStatus memberStatus) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.nickname = nickname;
        this.introduction = introduction;
        this.interestList = Objects.requireNonNullElseGet(interestList, () -> List.of(MemberInterest.BACKEND, MemberInterest.FRONTEND));
        this.memberStatus = Objects.requireNonNullElse(memberStatus, MemberStatus.NORMAL);
    }

    public void updateStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void updateInterests(List<MemberInterest> interestList) {
        this.interestList = interestList;
    }

    public void addInterest(MemberInterest interest) {
        this.interestList.add(interest);
    }
}
