package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //jpa query
    boolean existsByNameAndFilial_IdAndCourse_Id(String name, Long filialId, Long courseId);
}
