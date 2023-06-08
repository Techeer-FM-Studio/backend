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
@Table(name = "task_notification")
public class TaskNotification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_notification_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_member_id", nullable = false)
    private SharedMember sharedMember;

    @Column(nullable = false)
    private String receiverNickname;

    @Column(nullable = false)
    private String senderNickname;

    @Column(nullable = false)
    private Boolean readStatus;

    @Builder
    public TaskNotification(SharedMember sharedMember, String receiverNickname, String senderNickname, Boolean readStatus) {
        this.sharedMember = sharedMember;
        this.receiverNickname = receiverNickname;
        this.senderNickname = senderNickname;
        this.readStatus = readStatus;
    }

    public void deleteTaskNotification() {
        this.delete();
    }
}
