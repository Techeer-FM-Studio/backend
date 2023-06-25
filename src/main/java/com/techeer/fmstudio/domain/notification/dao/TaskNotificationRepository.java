package com.techeer.fmstudio.domain.notification.dao;

import com.techeer.fmstudio.domain.notification.domain.TaskNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskNotificationRepository extends JpaRepository<TaskNotification, Long> {

}
