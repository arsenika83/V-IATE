package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET favorite_group_ids = favorite_group_ids || :g_id WHERE s.id = :s_id")
    void addFavoriteGroup(@Param("s_id") long s_id, @Param("g_id") long g_id);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET favorite_group_ids = array_remove(favorite_group_ids, :g_id) WHERE s.id = :s_id")
    void removeFavoriteGroup(@Param("s_id") long s_id, @Param("g_id") long g_id);

    @Query("SELECT s FROM Student s WHERE s.id = :s_id AND :g_id IN (SELECT unnest(s.favorite_group_ids))")
    Student isFavoriteGroup(@Param("s_id") long s_id, @Param("g_id") long g_id);
}
