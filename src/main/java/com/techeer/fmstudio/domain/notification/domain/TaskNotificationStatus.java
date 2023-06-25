package com.techeer.fmstudio.domain.notification.domain;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "task_notification_status")
public class TaskNotificationStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_notification_status_id")
    private Long id;

    @Column(nullable = false)
    private Boolean acceptStatus;

    @Column(nullable = false)
    private Boolean readStatus;

    @Builder
    public TaskNotificationStatus(Boolean acceptStatus, Boolean readStatus) {
        this.acceptStatus = acceptStatus;
        this.readStatus = readStatus;
    }

    public void deleteTaskNotificationStatus() {
        this.delete();
    }
}
