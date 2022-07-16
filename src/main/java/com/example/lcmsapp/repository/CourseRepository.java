package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Course;
import com.example.lcmsapp.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "course")
public interface CourseRepository extends JpaRepository<Course, Long> {
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @Override
    <S extends Course> S save(S entity);
}
