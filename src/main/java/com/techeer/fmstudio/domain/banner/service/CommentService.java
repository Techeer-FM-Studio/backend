package com.techeer.fmstudio.domain.banner.service;

import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.dao.CommentRepository;
import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.dto.request.CommentCreateRequest;
import com.techeer.fmstudio.domain.banner.exception.NotFoundBannerException;
import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BannerRepository bannerRepository;

    public CommentEntity create(CommentCreateRequest request, Long bannerId){
        MemberEntity foundMember = memberRepository.findMemberEntityByNickname(request.getWriter())
                .orElseThrow(NotFoundMemberException::new);

        BannerEntity foundBanner = bannerRepository.findById(bannerId)
                .orElseThrow(NotFoundBannerException::new);

        CommentEntity newComment = CommentEntity.builder()
                .writer(foundMember)
                .banner(foundBanner)
                .contents(request.getComments())
                .build();

        return commentRepository.save(newComment);
    }
}
