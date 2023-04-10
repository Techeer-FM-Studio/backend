package com.techeer.fmstudio.domain.member.service;

import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.dto.MemberCreateRequest;
import com.techeer.fmstudio.domain.member.dto.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;

    public MemberEntity register(MemberCreateRequest request){
        return repository.save(mapper.toEntity(request));
    }

}
