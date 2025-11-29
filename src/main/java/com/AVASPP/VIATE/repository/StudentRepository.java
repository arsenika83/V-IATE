package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
