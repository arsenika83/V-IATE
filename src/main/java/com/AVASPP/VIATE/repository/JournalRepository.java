package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Journal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends CrudRepository<Journal, Long> {

    @Query("SELECT j FROM Journal j WHERE j.group_id = :id")
    Iterable<Journal> findAllByGroupId(@Param("id") long g_id);
}
