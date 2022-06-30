package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.AuthRole;
import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class User extends AbsEntity {

    @ManyToMany
    private Set<Role> roles;

//    @Enumerated
//    @ElementCollection
//    private Set<AuthRole> authRole;

    private String fullName, phone;

    private Double salary;

    private boolean active = true;

    @ManyToOne
    private Filial filial;

    @Enumerated
    private PositionType positionType;
}
