package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    @Query("SELECT id FROM Lesson WHERE day = :day")
    Optional<Schedule> findAllByDay(@Param("day") String day);
}
