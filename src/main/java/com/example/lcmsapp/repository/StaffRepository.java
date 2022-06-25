package com.example.lcmsapp.repository;

import com.example.lcmsapp.entity.Filial;
import com.example.lcmsapp.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;


//localhost/api/filial
@RepositoryRestResource(path = "staff")
public interface StaffRepository extends JpaRepository<Staff, UUID> {
    //alohida yana controller yozdik
}
