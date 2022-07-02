package com.example.lcmsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:28 PM on 6/26/2022
 * @project Edu-Center
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PaymentDto {

    @NotNull(message = "Studentni kirirtish shart")
    private String studentId;
    @NotNull(message = "Filialni kiritish kerak")
    private Long filialId;
    @NotNull(message = "summani kiritish shart")
    private Double amount;
    @NotNull(message = "payType tanlanmadi")
    private String payType;



}
