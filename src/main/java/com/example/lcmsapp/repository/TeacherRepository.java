package com.example.lcmsapp.repository;

import com.example.lcmsapp.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Page<Teacher> findAllByFullNameContainingIgnoreCaseAndPhoneContainingIgnoreCaseAndCourse_NameContainingIgnoreCase(String fullName, String phone, String course, Pageable pageable);
}
