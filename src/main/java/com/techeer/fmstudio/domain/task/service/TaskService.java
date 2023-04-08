package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.dao.TaskRepository;
import com.techeer.fmstudio.domain.task.dao.TestMemberRepository;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.request.TaskUpdateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {
    private final TestMemberRepository testMemberRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final SharedMemberService sharedMemberService;

    @Transactional
    public Task createTask(TaskCreateRequest taskCreateRequest) {
        Task task = taskMapper.mapTaskCreateRequestToTaskEntity(taskCreateRequest);
        Task savedTask = taskRepository.save(task);

        TestMember foundTestMember = testMemberRepository.findTestMemberByNickname(taskCreateRequest.getWriter())
                        .orElseThrow(EntityNotFoundException::new);
        sharedMemberService.createSharedMember(savedTask, foundTestMember);
        return savedTask;
    }

    @Transactional
    public TaskInfo updateTask(TaskUpdateRequest taskUpdateRequest) {
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
    public TaskInfo getTask(Long taskId) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(EntityNotFoundException::new);

        return taskMapper.mapTaskEntityToTaskInfo(foundTask);
    }
}
