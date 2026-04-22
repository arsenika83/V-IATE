package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Course;
import com.AVASPP.VIATE.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.group_id = :id")
    Iterable<Course> findAllByGroupId(@Param("id") long id);
}
