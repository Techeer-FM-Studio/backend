package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.task.dao.TaskRepository;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public Task createTask(TaskCreateRequest taskCreateRequest) {
        Task task = taskMapper.mapTaskCreateRequestToTaskEntity(taskCreateRequest);
        return taskRepository.save(task);
    }

}
