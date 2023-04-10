package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.TaskRepository;
import com.techeer.fmstudio.domain.task.dao.TestMemberRepository;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
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
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {
    private final TestMemberRepository testMemberRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final SharedMemberService sharedMemberService;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest taskCreateRequest) {
        Task task = taskMapper.mapTaskCreateRequestToTaskEntity(taskCreateRequest);
        Task savedTask = taskRepository.save(task);

        List<String> sharedMemberIdList = taskCreateRequest.getSharedMembersId();
        List<String> foundTestMemberList = new ArrayList<>();

        for(int i = 0; i < sharedMemberIdList.size(); i++) {
            TestMember foundTestMember = testMemberRepository.findTestMemberByNickname(sharedMemberIdList.get(i))
                    .orElseThrow(EntityNotFoundException::new);
            // TODO : foundTestMember를 리스트로 저장해서 createSharedMember 한번에 저장

            sharedMemberService.createSharedMember(savedTask, foundTestMember);
            foundTestMemberList.add(foundTestMember.getNickname());
        }

        savedTask.updateSharedMemberList(foundTestMemberList);

        // savedTask에 foundTestMemberList 저쟝하는 mapper 만들기
        // TODO : mapper를 여기서 써서 엔티티 도메인을 서비스 밖으로 내보내지 않는다.
        return taskMapper.mapTaskEntityToTaskInfo(savedTask, foundTestMemberList);
    }

    @Transactional
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        Task foundTask = taskRepository.findById(taskUpdateRequest.getTaskId())
                .orElseThrow(EntityNotFoundException::new);

        foundTask.updateTask(taskUpdateRequest);
        Task updatedTask = taskRepository.save(foundTask);

        return taskMapper.mapTaskEntityToTaskInfo(updatedTask);
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

        return taskMapper.mapTaskEntityToTaskInfo(foundTask);
    }
}
