package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.entity.enums.StudentStatus;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;
import org.hibernate.type.TimestampType;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Builder
public class Teacher extends AbsEntity {

    private String fullName, phone;

    private Double balance = 0.0;

    private boolean active = false;

    @ManyToOne
    private Course course;

    @Enumerated
    private PositionType positionType = PositionType.MENTOR;

    private Character male;

    @Column(length = 9)
    private String passportNo;

    private String address;

    private String passportGivenBy;

    private LocalDate passportDateOfIssue;

    private LocalDate dateOfBirth;
}
