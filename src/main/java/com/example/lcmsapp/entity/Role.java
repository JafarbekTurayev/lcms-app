package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.template.AbsNameEntity;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@ToString
public class Role extends AbsNameEntity {
    public Role(Long id, String name) {
        super(id, name);
    }

    public Role() {

    }
}
