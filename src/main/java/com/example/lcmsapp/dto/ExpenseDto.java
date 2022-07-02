package com.example.lcmsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExpenseDto {
    private String description;
    @NotNull(message = "ExpenseType kiritish majburiy")
    private String expenseType;
    @NotNull(message = "Filial kiritish majburiy")
    private Long filialId;
    @NotNull(message = "Summa kiritish majburiy")
    private Double amount;
    @NotNull(message = "Paytype kiritish majburiy")
    private String payType;
}
