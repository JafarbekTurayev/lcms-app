package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Page<Group> findAllByNameContainingIgnoreCase(String ketmon, Pageable pageable);

    Page<Group> findAllByFilial_NameContainingIgnoreCase(String name, Pageable pageable);

    Page<Group> findAllByCourse_NameContainingIgnoreCase(String course, Pageable pageable);

    Page<Group> findAllByFilial_NameContainingIgnoreCaseAndCourse_NameContainingIgnoreCaseAndNameContainingIgnoreCase(String filial, String course, String name, Pageable pageable);

    //jpa query
    boolean existsByNameAndFilial_IdAndCourse_Id(String name, Long filialId, Long courseId);
}
