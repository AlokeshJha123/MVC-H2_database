package com.alok.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok.Entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
