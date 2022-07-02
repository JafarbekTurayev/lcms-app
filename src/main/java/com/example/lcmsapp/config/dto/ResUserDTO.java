package com.example.lcmsapp.config.dto;

import com.example.lcmsapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResUserDTO {
    //Javob sifatida qaytarish uchun
    private Set<String> roles;

    private String fullName;

    private String phone;

    private Double salary;

    private String filialName;

    private String positionType;

}
