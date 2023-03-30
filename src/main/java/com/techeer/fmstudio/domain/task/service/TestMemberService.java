package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.TestMemberRepository;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.mapper.TestMemberMapper;
import com.techeer.fmstudio.domain.task.dto.request.TestMemberCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class TestMemberService {
    private final TestMemberRepository testMemberRepository;
    private final TestMemberMapper testMemberMapper;

    @Transactional
    public TestMember createTestMember(TestMemberCreateRequest testMemberCreateRequest) {
        TestMember testMember = testMemberMapper.mapTestMemberCreateRequestToTestMember(testMemberCreateRequest);
        return testMemberRepository.save(testMember);
    }
}