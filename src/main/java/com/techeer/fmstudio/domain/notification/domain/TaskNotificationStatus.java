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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_notification_id", nullable = false)
    private TaskNotification taskNotification;

    @Column(nullable = false)
    private Boolean acceptStatus;

    @Column(nullable = false)
    private Boolean readStatus;

    @Builder
    public TaskNotificationStatus(TaskNotification taskNotification, Boolean acceptStatus, Boolean readStatus) {
        this.taskNotification = taskNotification;
        this.acceptStatus = acceptStatus;
        this.readStatus = readStatus;
    }

    public void deleteTaskNotificationStatus() {
        this.delete();
    }
}
