package com.techeer.fmstudio.domain.banner.domain;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;

import javax.persistence.*;

public class MyBannerList extends BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    @ManyToOne
    @JoinColumn(name = "banner_id")
    private BannerEntity banner;
}
