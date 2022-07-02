package com.example.lcmsapp.controller;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.PaymentDto;
import com.example.lcmsapp.entity.Payment;
import com.example.lcmsapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author "Husniddin Ulachov"
 * @created 2:39 PM on 6/26/2022
 * @project Edu-Center
 */
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentControl {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        ApiResponse<Payment> apiResponse = paymentService.save(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam (defaultValue = "0")  int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "")  String filial,
            @RequestParam (defaultValue = "") String student,
            @RequestParam (defaultValue = "") String group,
            @RequestParam (defaultValue = "") String startDate,
            @RequestParam (defaultValue = "") String endDate
    ) {

        ApiResponse<?> apiResponse = paymentService.getAll(page,size,filial,student,group,startDate,endDate);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        ApiResponse<Payment> one = paymentService.getOne(id);
        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam String id, @Valid @RequestBody PaymentDto paymentDto) {
        ApiResponse<Payment> apiResponse = paymentService.update(id, paymentDto);
        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        ApiResponse<String> delete = paymentService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
