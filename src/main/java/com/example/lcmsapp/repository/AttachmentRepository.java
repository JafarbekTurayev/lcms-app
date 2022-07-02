package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Attachment;
import com.example.lcmsapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
