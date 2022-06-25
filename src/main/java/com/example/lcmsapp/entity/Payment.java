package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.PayType;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
@ToString
public class Payment extends AbsEntity {

    @ManyToOne
    private Student student;
    @ManyToOne
    private Filial filial;
    private Double amount;
    @Enumerated
    private PayType payType;
}
