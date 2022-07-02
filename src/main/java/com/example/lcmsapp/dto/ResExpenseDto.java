package com.example.lcmsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResExpenseDto {
    private String description;
    private String expenseType;
    private String filialName;
    private Double amount;
    private String payType;
}
