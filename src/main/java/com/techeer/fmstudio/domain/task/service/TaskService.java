package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.dao.TaskRepository;
import com.techeer.fmstudio.domain.task.dao.TestMemberRepository;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
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
    private final SharedMemberRepository sharedMemberRepository;
    private final TestMemberRepository testMemberRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final SharedMemberService sharedMemberService;
    private final SharedMemberMapper sharedMemberMapper;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest taskCreateRequest) {
        Task task = taskMapper.mapTaskCreateRequestToTaskEntity(taskCreateRequest);
        Task savedTask = taskRepository.save(task);

        List<String> sharedMemberNicknameList = taskCreateRequest.getSharedMembersNicknameList();
        List<String> foundTestMemberList = new ArrayList<>();

        for(int i = 0; i < sharedMemberNicknameList.size(); i++) {
            TestMember foundTestMember = testMemberRepository.findTestMemberByNickname(sharedMemberNicknameList.get(i))
                    .orElseThrow(EntityNotFoundException::new);

            sharedMemberService.createSharedMember(savedTask, foundTestMember);
            foundTestMemberList.add(foundTestMember.getNickname());
        }

        savedTask.updateSharedMemberList(foundTestMemberList);

        return taskMapper.mapTaskEntityToTaskResponse(savedTask, foundTestMemberList);
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
    public List<TaskResponse> getWriterTaskByYearAndMonth(String memberId, Integer year, Integer month) {
        return taskRepository.findTasksByMemberIdAndStartAtOrderByStartAt(memberId, year, month)
                .stream()
                .map(taskMapper::mapTaskEntityToTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getSharedTaskByYearAndMonth(String memberId, Integer year, Integer month) {
        TestMember foundMemberId = testMemberRepository.findTestMemberByNickname(memberId)
                .orElseThrow(EntityNotFoundException::new);

        List<Long> taskIdList = sharedMemberRepository.findSharedMembersByTestMember(foundMemberId)
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
