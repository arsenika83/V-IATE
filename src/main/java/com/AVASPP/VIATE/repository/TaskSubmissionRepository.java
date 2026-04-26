package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Task;
import com.AVASPP.VIATE.entity.TaskSubmission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskSubmissionRepository extends CrudRepository<TaskSubmission, Long> {

    @Query("SELECT t FROM TaskSubmission t WHERE t.task_id = :t_id AND t.user_id = :u_id")
    TaskSubmission findByTaskIdAndUserId(@Param("t_id") long t_id, @Param("u_id") long u_id);

    @Query("SELECT t.file_path FROM TaskSubmission t WHERE t.task_id = :t_id AND t.user_id = :u_id")
    String findPathByTaskIdAndUserId(@Param("t_id") long t_id, @Param("u_id") long u_id);

    @Modifying
    @Transactional
    @Query("UPDATE TaskSubmission t SET status = :status WHERE t.id = :id")
    void updateStatus(@Param("id") long id, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("DELETE FROM TaskSubmission t WHERE t.task_id = :t_id AND t.user_id = :u_id")
    void deleteByTaskIdAndUserId(@Param("t_id") long task_id, @Param("u_id") long user_id);

    @Query("SELECT COUNT(task_id) FROM TaskSubmission t WHERE t.task_id = :t_id AND t.user_id = :u_id")
    int countByTaskIdAndUserId(@Param("t_id") long task_id, @Param("u_id") long user_id);
}
