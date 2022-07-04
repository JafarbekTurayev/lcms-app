package com.example.lcmsapp.repository;

import com.example.lcmsapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByFullNameContainingIgnoreCase(String name);
}
