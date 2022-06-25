package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Course;
import com.example.lcmsapp.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
