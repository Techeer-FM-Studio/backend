package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import com.techeer.fmstudio.domain.task.exception.NotFoundSharedMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class SharedMemberService {
    private final SharedMemberRepository sharedMemberRepository;
    private final SharedMemberMapper sharedMemberMapper;


    @Transactional
    public void createSharedMember(Task task, MemberEntity memberEntity) {
        SharedMember sharedMember = SharedMember.builder()
                .task(task)
                .memberEntity(memberEntity)
                .build();

        sharedMemberRepository.save(sharedMember);
    }

    @Transactional
    public void deleteSharedMember(Long taskId) {
        SharedMember foundSharedMember = sharedMemberRepository.findSharedMemberByTask(taskId)
                .orElseThrow(NotFoundSharedMemberException::new);

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
