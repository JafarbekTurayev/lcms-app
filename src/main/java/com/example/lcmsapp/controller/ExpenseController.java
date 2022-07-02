package com.example.lcmsapp.controller;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.ExpenseDto;
import com.example.lcmsapp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseServise;
    @PostMapping
    public ResponseEntity<ApiResponse> save( @Valid @RequestBody ExpenseDto expenseDto){
        ApiResponse apiResponse = expenseServise.add(expenseDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:404).body(apiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        ApiResponse response = expenseServise.getall(page,size);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable UUID id){
        ApiResponse response = expenseServise.getone(id);
        return ResponseEntity.status(response.isSuccess()? 201:404).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
        ApiResponse response = expenseServise.delete(id);
        return ResponseEntity.status(response.isSuccess()? 201:404).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable UUID id, @RequestBody ExpenseDto expenseDto){
        ApiResponse response = expenseServise.update(id,expenseDto);
        return ResponseEntity.status(response.isSuccess()? 201:404).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
