package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.course_id = :id")
    Iterable<Task> findAllByCourseId(@Param("id") long id);

    @Query("UPDATE Task t SET status = :status WHERE t.id = :id")
    void updateStatus(@Param("id") long id, @Param("status") String status);
}
