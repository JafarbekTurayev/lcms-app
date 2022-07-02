package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Role;
import com.example.lcmsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
