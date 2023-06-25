package com.techeer.fmstudio.domain.notification.dao;

import com.techeer.fmstudio.domain.notification.domain.TaskNotification;
import com.techeer.fmstudio.domain.notification.domain.TaskNotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskNotificationStatusRepository extends JpaRepository<TaskNotificationStatus, Long> {

}
