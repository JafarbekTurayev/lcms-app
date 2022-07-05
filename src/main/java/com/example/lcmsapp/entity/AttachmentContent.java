package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AttachmentContent extends AbsEntity {

    @OneToOne
    private Attachment attachment;
    private byte[] bytes;
}
