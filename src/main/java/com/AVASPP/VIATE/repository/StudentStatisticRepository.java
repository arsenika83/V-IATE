package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.StudentStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentStatisticRepository extends CrudRepository<StudentStatistic, Long> {
    @Query("SELECT ss FROM StudentStatistic ss WHERE ss.student_id = :s_id AND ss.journal_id = :j_id")
    StudentStatistic findByStudentIdAndJournalId(@Param("s_id") long s_id, @Param("j_id") long j_id);

    @Query("SELECT mark FROM StudentStatistic ss WHERE ss.student_id = :s_id")
    List<Integer> getMarksByStudentId(@Param("s_id") long s_id);
}
