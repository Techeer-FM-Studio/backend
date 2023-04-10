package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class SharedMemberService {
    private final SharedMemberRepository sharedMemberRepository;
    private final SharedMemberMapper sharedMemberMapper;


    @Transactional
    public void createSharedMember(Task task, TestMember testMember) {
        SharedMember sharedMember = SharedMember.builder()
                .task(task)
                .testMember(testMember)
                .build();

        sharedMemberRepository.save(sharedMember);
    }

    @Transactional
    public void deleteSharedMember(Long taskId) {
        SharedMember foundSharedMember = sharedMemberRepository.findSharedMemberByTask(taskId)
                .orElseThrow(EntityNotFoundException::new);

        foundSharedMember.deleteSharedMember();
    }

    @Transactional(readOnly = true)
    public List<SharedMemberResponse> getSharedMemberList(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return sharedMemberRepository.findSharedMembersWithPagination(pageRequest)
                .stream()
                .map(sharedMemberMapper::mapSharedMemberEntityToSharedMemberInfo)
                .collect(Collectors.toList());
    }
}
