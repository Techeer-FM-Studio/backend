package com.techeer.fmstudio.domain.banner.dao;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MyBannerListRepository extends JpaRepository<MyBannerList, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MyBannerList m WHERE m.banner.id = :bannerId " +
            "AND m.member.id = :memberId")
    void deleteByMemberIdAndBannerId(
            @Param("memberId") Long memberId,
            @Param("bannerId") Long bannerId
    );

    @Query("SELECT m.banner FROM MyBannerList m WHERE m.member.loginId = :memberId " +
            "AND year(m.banner.startAt) = :year " +
            "AND month(m.banner.startAt) = :month " +
            "AND m.isActive = true " +
            "ORDER BY m.banner.startAt asc")
    List<BannerEntity> findMyBannerByMemberIdAndYearAndMonth(String memberId, int year, int month);
}