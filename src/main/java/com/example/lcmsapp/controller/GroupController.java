package com.example.lcmsapp.controller;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.GroupDTO;
import com.example.lcmsapp.entity.Group;
import com.example.lcmsapp.repository.GroupRepository;
import com.example.lcmsapp.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    //save
//    @PreAuthorize(value = "hasAuthority('WRITE_GROUP')")
//    -> permission based
    @PreAuthorize(value = "hasAnyRole('ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody GroupDTO groupDTO) {
        //Restcontroller Adviceni o'tish kerak oddiy exception ishlamadi
        ApiResponse response = groupService.add(groupDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    //getOne
//    @PreAuthorize(value ="hasAnyRole('USER','ADMIN','MANAGER')") -> rolebased
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = groupService.getOne(id);
        return ResponseEntity.ok(response);
    }

    //getAll va pagination va search va filtr byCourse filterbyFilial
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String search, @RequestParam(value = "filial", defaultValue = "") String filialName, @RequestParam(value = "course", defaultValue = "") String courseName) {
        ApiResponse response = groupService.getAll(page, size, search, filialName, courseName);
        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
        ApiResponse response = groupService.edit(id, groupDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = groupService.remove(id);
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
