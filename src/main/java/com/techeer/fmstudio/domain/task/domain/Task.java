package com.techeer.fmstudio.domain.task.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.techeer.fmstudio.domain.task.dto.request.TaskUpdateRequest;
import com.techeer.fmstudio.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "task")
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    // TODO : banner 엔티티 외래키 연결

    @OneToMany(mappedBy = "task")
    private Set<SharedMember> sharedMemberSet;

    @Column(nullable = false)
    @NotBlank
    private String writer;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startAt;

    @Column(nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endAt;

    @Column(nullable = false)
    @NotNull
    private Boolean isFinished;

    @Column(nullable = false)
    @NotNull
    private Boolean isOpened;

    @Builder
    public Task(String writer, String title, String memo, LocalDateTime startAt, LocalDateTime endAt,
                Boolean isFinished, Boolean isOpened) {
        this.writer = writer;
        this.title = title;
        this.memo = memo;
        this.startAt = startAt;
        this.endAt = endAt;
        this.isFinished = isFinished;
        this.isOpened = isOpened;
    }

    public void updateTask(TaskUpdateRequest taskUpdateRequest) {
        this.title = taskUpdateRequest.getTitle();
        this.memo = taskUpdateRequest.getMemo();
        this.startAt = taskUpdateRequest.getStartAt();
        this.endAt = taskUpdateRequest.getEndAt();
        this.isFinished = taskUpdateRequest.getIsFinished();
        this.isOpened = taskUpdateRequest.getIsOpened();
    }

    public void deleteTask() {
        this.delete();
    }
}
