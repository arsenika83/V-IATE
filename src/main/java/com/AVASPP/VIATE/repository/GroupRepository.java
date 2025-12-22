package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Query("SELECT g FROM Group g WHERE g.name like %:year%")
    Iterable<Group> findAllByCourse(@Param("year") int year);
}
