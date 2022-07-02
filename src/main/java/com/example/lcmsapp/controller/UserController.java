package com.example.lcmsapp.controller;

import com.example.lcmsapp.config.dto.ApiResponse;
import com.example.lcmsapp.config.dto.UserDTO;
import com.example.lcmsapp.repository.UserRepository;
import com.example.lcmsapp.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userServise;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserDTO user) {
        ApiResponse response = userServise.add(user);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = userServise.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") UUID uuid){
        ApiResponse response = userServise.getOne(uuid);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") UUID uuid,@Valid @RequestBody UserDTO user){
        ApiResponse response = userServise.update(uuid,user);
        return ResponseEntity.status(response.isSuccess() ? 202 : 400).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ApiResponse response = userServise.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    //validation ishlashi un metod
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
