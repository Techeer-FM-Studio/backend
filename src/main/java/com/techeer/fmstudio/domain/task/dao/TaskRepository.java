package com.techeer.fmstudio.domain.task.dao;

import com.techeer.fmstudio.domain.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.id = :id and t.isActive = true")
    Optional<Task> findById(Long id);

    @Query("select t from Task t where t.writer = :memberId " +
            "and year(t.startAt) = :year " +
            "and month(t.startAt) = :month " +
            "and t.isActive = true " +
            "order by t.startAt")
    List<Task> findTasksByMemberIdAndStartAtOrderByStartAt(String memberId, Integer year, Integer month);

    @Query("select t from Task t where t.id = :id " +
            "and year(t.startAt) = :year " +
            "and month(t.startAt) = :month " +
            "and t.isActive = true " +
            "order by t.startAt")
    Optional<Task> findTaskByIdAndStartAtOrOrderByStartAt(Long id, Integer year, Integer month);

    @Query(nativeQuery = true, value = "select * from task where writer = :memberId " +
            "and is_active = true " +
            "and start_at between :lastDayOfMonth and DATE_ADD(:lastDayOfMonth, INTERVAL :remainingDay DAY) " +
            "order by start_at asc")
    List<Task> findTaskOfAfterMonth(String memberId, Date lastDayOfMonth, int remainingDay);

    @Query(nativeQuery = true, value = "select * from task where writer = :memberId " +
            "and is_active = true " +
            "and start_at between DATE_SUB(:firstDateOfMonth, INTERVAL :remainingDay DAY) and :firstDateOfMonth " +
            "order by start_at asc")
    List<Task> findTaskOfBeforeMonth(String memberId, Date firstDateOfMonth, int remainingDay);
}
