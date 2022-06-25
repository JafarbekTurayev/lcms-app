package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.entity.enums.StudentStatus;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Builder
public class Teacher extends AbsEntity {

    private String fullName, phone;

    private Double balance;

    private boolean active;

    @ManyToOne
    private Course course;

    @Enumerated
    private PositionType positionType = PositionType.MENTOR;
}
