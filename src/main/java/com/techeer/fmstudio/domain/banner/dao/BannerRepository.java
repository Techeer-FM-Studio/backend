package com.techeer.fmstudio.domain.banner.dao;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<BannerEntity, Long> {

    @Query("SELECT b FROM BannerEntity b WHERE b.isActive = true AND b.id = :bannerId")
    Optional<BannerEntity> findById(@Param("bannerId") Long bannerId);

    @Query("SELECT b FROM BannerEntity b WHERE b.isActive = true " +
            "AND LOWER(b.bannerType) = LOWER(:#{#type.name()})" +
            "ORDER BY b.createdAt asc")
    Page<BannerEntity> findBannerByTypeWithPagination(Pageable pageable, @Param("type") BannerType type);

}
