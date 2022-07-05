package com.example.lcmsapp.dto;

import com.example.lcmsapp.entity.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherDTO {
    @NotNull(message = "FIO to'ldirish shart")
    private String fullName;

    private Long courseId;

    private boolean active;

    private Double balance; //12

    @NotNull(message = "Address to'ldirish shart")
    private String address;

    @NotNull(message = "Tug'ilgan sanani to'ldirish shart")
    private String dateOfBirth;

    @NotNull(message = "Jinsingizni to'ldirish shart")
    private Character male;

    @NotNull(message = "Passport berilgan sanasini to'ldirish shart")
    private String passportDateOfIssue;

    @NotNull(message = "Passport kim tomonidan berilganini to'ldirish shart")
    private String passportGivenBy;

    @NotNull(message = "Passport seriya raqamini to'ldirish shart")
    private String passportNo;

    @NotNull(message = "Telefon raqam to'ldirish shart")
    private String phone;

    private PositionType positionType;
}
