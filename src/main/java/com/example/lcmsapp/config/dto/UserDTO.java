package com.example.lcmsapp.config.dto;

import com.example.lcmsapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

//    @NotNull(message = "Nomini to'ldirish shart")
    private List<Long> rolesId;

    private String fullName;

    private String phone;

    @NotNull(message = "Maoshni tanlash majburiy")
    private Double salary;

    @NotNull(message = "Filialni tanlash majburiy")
    private Long filialId;

    @NotNull(message = "PositionTypeni tanlash majburiy")
    private String positionType;

}
