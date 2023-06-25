package com.techeer.fmstudio.domain.task.service;

import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import com.techeer.fmstudio.domain.notification.domain.TaskNotification;
import com.techeer.fmstudio.domain.notification.dto.request.TaskNotificationCreateRequest;
import com.techeer.fmstudio.domain.notification.service.TaskNotificationService;
import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.dao.TaskRepository;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.request.TaskUpdateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskResponse;
import com.techeer.fmstudio.domain.task.exception.NotFoundTaskException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    private final TaskNotificationService taskNotificationService;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest taskCreateRequest) {
        Task task = taskMapper.mapTaskCreateRequestToTaskEntity(taskCreateRequest);
        Task savedTask = taskRepository.save(task);

        List<String> sharedMemberNicknameList = taskCreateRequest.getSharedMembersNicknameList();
        List<String> foundSharedMemberList = new ArrayList<>();
        List<SharedMember> validatedSharedMemberList = new ArrayList<>();

        for (String s : sharedMemberNicknameList) {
            MemberEntity foundMember = memberRepository.findMemberEntityByNickname(s)
                .orElseThrow(NotFoundMemberException::new);

            SharedMember sharedMember = sharedMemberService.createSharedMember(savedTask,
                foundMember);
            foundSharedMemberList.add(foundMember.getNickname());
            validatedSharedMemberList.add(sharedMember);
        }

        savedTask.updateSharedMemberList(foundSharedMemberList);

        for (SharedMember sharedMember : validatedSharedMemberList) {
            TaskNotificationCreateRequest taskNotificationCreateRequest = TaskNotificationCreateRequest.builder()
                .sharedMember(sharedMember)
                .receiverNickname(sharedMember.getMemberEntity().getNickname())
                .senderNickname(sharedMember.getTask().getWriter())
                .build();
            taskNotificationService.createTaskNotification(taskNotificationCreateRequest);
        }

        return taskMapper.mapTaskEntityToTaskResponse(savedTask, foundSharedMemberList);
    }

    @Transactional
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        Task foundTask = taskRepository.findById(taskUpdateRequest.getTaskId())
                .orElseThrow(NotFoundTaskException::new);

        foundTask.updateTask(taskUpdateRequest);
        Task updatedTask = taskRepository.save(foundTask);

        return taskMapper.mapTaskEntityToTaskResponse(updatedTask);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(NotFoundTaskException::new);

        foundTask.deleteTask();
        sharedMemberService.deleteSharedMember(taskId);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long taskId) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(NotFoundTaskException::new);

        return taskMapper.mapTaskEntityToTaskResponse(foundTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getPrivateTaskAndSharedTask(String memberId, Integer year, Integer month) {
        List<TaskResponse> foundTaskResponseList = new ArrayList<>();
        
        foundTaskResponseList.addAll(getWriterTaskByYearAndMonth(memberId, year, month));
        foundTaskResponseList.addAll(getSharedTaskByYearAndMonth(memberId, year, month));
        foundTaskResponseList.addAll(getPreviousMonthTask(memberId, year, month));
        foundTaskResponseList.addAll(getNextMonthTask(memberId, year, month));

        return foundTaskResponseList
                .stream()
                .sorted(Comparator.comparing(TaskResponse::getStartAt))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getPreviousMonthTask(String memberId, int year, int month) {
        int numberOfDay = getNumberOfFirstDay(year, month); // 월요일은 1, 일요일은 7
        int remainingDay = getRemainingDay(numberOfDay);
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        Date firstDateOfMonth = Date.valueOf(firstDayOfMonth); // 해당 월의 첫번째 날짜

        return taskRepository.findTaskOfBeforeMonth(memberId, firstDateOfMonth, remainingDay)
                .stream()
                .map(taskMapper::mapTaskEntityToTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getNextMonthTask(String memberId, int year, int month) {
        int numberOfDay = getNumberOfLastDay(year, month); // 월요일은 1, 일요일은 7
        int remainingDay = getRemainingDay(numberOfDay);
        int lastDayOfMonth = getLastDayOfMonth(year, month); // 해당 월의 마지막 날짜
        LocalDate date = LocalDate.of(year, month, lastDayOfMonth);
        Date lastDateOfMonth = Date.valueOf(date);

        return taskRepository.findTaskOfAfterMonth(memberId, lastDateOfMonth, remainingDay)
                .stream()
                .map(taskMapper::mapTaskEntityToTaskResponse)
                .collect(Collectors.toList());
    }

    public int getRemainingDay(int numberOfDay) {
        if(numberOfDay == 7) {
            return 0;
        } else {
            return numberOfDay;
        }
    }

    public int getNumberOfLastDay(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = LocalDate.of(year, month, firstDayOfMonth.lengthOfMonth());
        DayOfWeek dayOfWeek = lastDayOfMonth.getDayOfWeek();
        return dayOfWeek.getValue(); // 월요일은 1, 일요일은 7
    }

    public int getNumberOfFirstDay(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue(); // 월요일은 1, 일요일은 7
    }

    public int getLastDayOfMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth(); // 해당 월의 마지막 날짜
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
                .orElseThrow(NotFoundTaskException::new);

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
