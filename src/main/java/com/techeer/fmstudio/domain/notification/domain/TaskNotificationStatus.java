package com.techeer.fmstudio.domain.notification.domain;

import com.techeer.fmstudio.domain.task.domain.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "task_notification_status")
public class TaskNotificationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_notification_status_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_notification_id", nullable = false)
    private TaskNotification task;

    @Column(nullable = false)
    private Boolean acceptStatus;

    @Column(nullable = false)
    private Boolean readStatus;
}
