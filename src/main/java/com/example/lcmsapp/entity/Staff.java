package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Staff extends AbsEntity {
    private String fullName, phone;

    private Double salary;

    private boolean active = true;

    @ManyToOne
    private Filial filial;

    @Enumerated
    private PositionType positionType;
}
