package com.example.lcmsapp.repository;

import com.example.lcmsapp.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;


//localhost/api/filial
@RepositoryRestResource(path = "filial")
public interface FilialRepository extends JpaRepository<Filial, Long> {
    //alohida yana controller yozdik
    @RestResource(path = "some")
    List<Filial> findAllByNameStartsWith(@Param("name") String name);

    @RestResource(path = "name")
    Optional<Filial> findByNameContainingIgnoreCase(@Param("name") String name);
}
