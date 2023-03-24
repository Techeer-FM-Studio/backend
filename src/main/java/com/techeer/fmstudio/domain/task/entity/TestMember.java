package com.techeer.fmstudio.domain.task.entity;

import com.techeer.fmstudio.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "test_member")
public class TestMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_member_id")
    private Long id;

    @OneToMany(mappedBy = "testMember")
    private Set<SharedMember> sharedMemberSet;

    @Column(nullable = false)
    @NotBlank
    private String loginId;

    @Column(nullable = false)
    @NotBlank
    private String loginPassword;

    @Column(nullable = false)
    @NotBlank
    private String nickname;

    @Column(nullable = false)
    @ColumnDefault("현재 상태를 알려주세요.")
    private String status;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private String interests;

    @Builder
    public TestMember(String loginId, String loginPassword, String nickname,
                      String status, String introduction, String interests) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.nickname = nickname;
        this.status = status;
        this.introduction = introduction;
        this.interests = interests;
    }
}
