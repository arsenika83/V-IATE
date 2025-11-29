package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
