package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.ExpenseDto;
import com.example.lcmsapp.dto.ResExpenseDto;
import com.example.lcmsapp.entity.Expense;
import com.example.lcmsapp.entity.Filial;
import com.example.lcmsapp.entity.enums.ExpenseType;
import com.example.lcmsapp.entity.enums.PayType;
import com.example.lcmsapp.exception.ResourceNotFoundException;
import com.example.lcmsapp.repository.ExpenseRepository;
import com.example.lcmsapp.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    public static List<ExpenseType> list=new ArrayList<>(Arrays.asList(ExpenseType.values()));
    public static List<PayType> list1=new ArrayList<>(Arrays.asList(PayType.values()));
    public final ExpenseRepository expenseRepository;
    public final FilialRepository filialRepository;
    public ApiResponse add(ExpenseDto expenseDto) {
        Expense expense=new Expense();
        int index = list.indexOf(ExpenseType.valueOf(expenseDto.getExpenseType().toUpperCase()));
        if (index>=0) expense.setExpenseType(list.get(index));
//        else new ResourceNotFoundException("Expense","expenseType",expenseDto.getExpenseType());
        int index1 = list1.indexOf(PayType.valueOf(expenseDto.getPayType().toUpperCase()));
        if (index1>=0) expense.setPayType(list1.get(index1));
//        else new ResourceNotFoundException("Paytype","expensePayType",expenseDto.getPayType());
//        Filial filial = filialRepository.findById(expenseDto.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("Expense","filialId",expenseDto.getFilialId()));
        Filial filial = filialRepository.findById(expenseDto.getFilialId()).orElseThrow(() -> new RuntimeException("Filial Not Found"));
        expense.setFilial(filial);
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        Expense save = expenseRepository.save(expense);
        if (save!=null) {
            ResExpenseDto resExpenseDto = getResExpenseDto(save);
            return ApiResponse.builder().data(resExpenseDto).message("Added").success(true).build();
        }
        return ApiResponse.builder().success(false).message("Xatolik yuz berdi").build();
    }

    public ApiResponse getall(int page,int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Expense> all = expenseRepository.findAll(request);
        List<ResExpenseDto> list1=new ArrayList<>();
        for (Expense expense : all) {
            ResExpenseDto expenseDto = getResExpenseDto(expense);
            list1.add(expenseDto);
        }
        return ApiResponse.builder().message("Expense List").success(true).data(list1).build();
    }

    public ApiResponse getone(UUID uuid) {
        Expense expense = expenseRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Expense Not Found"));
        ResExpenseDto resExpenseDto = getResExpenseDto(expense);
        return ApiResponse.builder().data(resExpenseDto).success(true).message("Expense ").build();

    }

    public ApiResponse delete(UUID id) {
        if (!expenseRepository.existsById(id))return ApiResponse.builder().success(false).message("Expense not found").build();
        expenseRepository.deleteById(id);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }


    public ApiResponse update(UUID id, ExpenseDto expenseDto) {
        Optional<Expense> optional = expenseRepository.findById(id);
        if (optional.isPresent()){
            Expense expense = optional.get();
            int index = list.indexOf(ExpenseType.valueOf(expenseDto.getExpenseType().toUpperCase()));
            if (index>=0) expense.setExpenseType(list.get(index));
            int index1 = list1.indexOf(PayType.valueOf(expenseDto.getPayType()));
            if (index1>=0) expense.setPayType(list1.get(index1));
            Filial filial = filialRepository.findById(expenseDto.getFilialId()).orElseThrow(() -> new RuntimeException("Filial Not Found"));
            expense.setFilial(filial);
            expense.setAmount(expenseDto.getAmount());
            expense.setDescription(expenseDto.getDescription());
            Expense save = expenseRepository.save(expense);
            if (save!=null){
                ResExpenseDto resExpenseDto = getResExpenseDto(save);
                return ApiResponse.builder().data(resExpenseDto).message("Updated").success(true).build();
            }
        }
        return ApiResponse.builder().message("Xatolik yuz berdi").success(false).build();
    }

    //Entity To ResExpenseDto
    public static ResExpenseDto getResExpenseDto(Expense expense){
        return ResExpenseDto.builder().expenseType(String.valueOf(expense.getExpenseType()))
                .amount(expense.getAmount()).payType(String.valueOf(expense.getPayType()))
                .filialName(expense.getFilial().getName()).description(expense.getDescription()).build();
    }


}
