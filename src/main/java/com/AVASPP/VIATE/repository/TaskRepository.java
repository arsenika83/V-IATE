package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.course_id = :id ORDER BY id ASC ")
    Iterable<Task> findAllByCourseId(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET submission_id = submission_id || :s_id WHERE t.id = :id")
    void updateSubmissionIds(@Param("id") long id, @Param("s_id") Long s_id);
}
