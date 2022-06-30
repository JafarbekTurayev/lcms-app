package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Course;
import com.example.lcmsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
