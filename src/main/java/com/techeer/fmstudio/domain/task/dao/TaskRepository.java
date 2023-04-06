package com.techeer.fmstudio.domain.task.dao;

import com.techeer.fmstudio.domain.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.id = :id and t.isActive = true")
    Optional<Task> findById(Long id);
}
