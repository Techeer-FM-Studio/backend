package com.techeer.fmstudio.domain.banner.service;

import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.dao.CommentRepository;
import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.dto.mapper.CommentMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CommentCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.response.CommentPageInfo;
import com.techeer.fmstudio.domain.banner.exception.NotFoundBannerException;
import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomCommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    private final BannerRepository bannerRepository;
    private final CommentMapper commentMapper;

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

    @Transactional(readOnly = true)
    public CommentPageInfo getCustomBannerCommentByPagination(Long bannerId, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CommentEntity> pageResult = commentRepository.findCommentEntityByBannerIdWithPagination(pageRequest, bannerId);
        return commentMapper.mapEntityToCommentPageInfo(pageResult, page, size);
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }
}
