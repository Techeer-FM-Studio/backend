package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
@Slf4j
public class SharedMemberService {
    private final SharedMemberRepository sharedMemberRepository;

    @Transactional
    public void createSharedMember(Task task, TestMember testMember) {
        SharedMember sharedMember = SharedMember.builder()
                .task(task)
                .testMember(testMember)
                .build();

        sharedMemberRepository.save(sharedMember);
    }


}
