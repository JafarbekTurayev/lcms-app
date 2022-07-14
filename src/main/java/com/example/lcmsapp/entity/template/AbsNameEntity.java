package com.example.lcmsapp.entity.template;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@MappedSuperclass
public class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
