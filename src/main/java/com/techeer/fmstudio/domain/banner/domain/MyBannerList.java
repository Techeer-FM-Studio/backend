package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "my_banner_list")
public class MyBannerList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banner_id")
    private BannerEntity banner;

    @Builder
    public MyBannerList(MemberEntity member, BannerEntity banner){
        this.member = member;
        this.banner = banner;
    }
}
