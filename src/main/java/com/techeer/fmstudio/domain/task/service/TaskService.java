package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.dao.TaskRepository;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.request.TaskUpdateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {
    private final MemberRepository memberRepository;
    private final SharedMemberRepository sharedMemberRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final SharedMemberService sharedMemberService;
    private final SharedMemberMapper sharedMemberMapper;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest taskCreateRequest) {
        Task task = taskMapper.mapTaskCreateRequestToTaskEntity(taskCreateRequest);
        Task savedTask = taskRepository.save(task);

        List<String> sharedMemberNicknameList = taskCreateRequest.getSharedMembersNicknameList();
        List<String> foundMemberList = new ArrayList<>();

        for(int i = 0; i < sharedMemberNicknameList.size(); i++) {
            MemberEntity foundMember = memberRepository.findMemberEntityByNickname(sharedMemberNicknameList.get(i))
                    .orElseThrow(EntityNotFoundException::new);

            sharedMemberService.createSharedMember(savedTask, foundMember);
            foundMemberList.add(foundMember.getNickname());
        }

        savedTask.updateSharedMemberList(foundMemberList);

        return taskMapper.mapTaskEntityToTaskResponse(savedTask, foundMemberList);
    }

    @Transactional
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        Task foundTask = taskRepository.findById(taskUpdateRequest.getTaskId())
                .orElseThrow(EntityNotFoundException::new);

        foundTask.updateTask(taskUpdateRequest);
        Task updatedTask = taskRepository.save(foundTask);

        return taskMapper.mapTaskEntityToTaskResponse(updatedTask);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(EntityNotFoundException::new);

        foundTask.deleteTask();
        sharedMemberService.deleteSharedMember(taskId);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long taskId) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(EntityNotFoundException::new);

        return taskMapper.mapTaskEntityToTaskResponse(foundTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getPrivateTaskAndSharedTask(String memberId, Integer year, Integer month) {
        //TODO : 추가한 배너 리스트도 합쳐야함.
        List<TaskResponse> foundTaskResponseList = new ArrayList<>();
        
        foundTaskResponseList.addAll(getWriterTaskByYearAndMonth(memberId, year, month));
        foundTaskResponseList.addAll(getSharedTaskByYearAndMonth(memberId, year, month));

        return foundTaskResponseList
                .stream()
                .sorted(Comparator.comparing(TaskResponse::getStartAt))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getWriterTaskByYearAndMonth(String memberId, Integer year, Integer month) {
        return taskRepository.findTasksByMemberIdAndStartAtOrderByStartAt(memberId, year, month)
                .stream()
                .map(taskMapper::mapTaskEntityToTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getSharedTaskByYearAndMonth(String memberId, Integer year, Integer month) {
        MemberEntity foundMemberId = memberRepository.findMemberEntityByNickname(memberId)
                .orElseThrow(EntityNotFoundException::new);

        List<Long> taskIdList = sharedMemberRepository.findSharedMembersByMemberEntity(foundMemberId)
                .stream()
                .map(sharedMemberMapper::mapSharedMemberEntityToTaskEntity)
                .toList();

        List<Task> validTasks = new ArrayList<>();

        for (Long taskId : taskIdList) {
            if(taskRepository.findTaskByIdAndStartAtOrOrderByStartAt(taskId, year, month)
                    .isPresent()) {
                validTasks.add(taskRepository.findTaskByIdAndStartAtOrOrderByStartAt(taskId, year, month).get());
            }
        }

        return validTasks
                .stream()
                .map(taskMapper::mapTaskEntityToTaskResponse)
                .sorted(Comparator.comparing(TaskResponse::getStartAt))
                .toList();
    }


}
