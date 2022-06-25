package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.StudentStatus;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
public class Student extends AbsEntity {
    private String fullName, phone;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @ManyToMany
    private List<Group> groups;
    //filtr
}
