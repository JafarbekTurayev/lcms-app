package com.example.lcmsapp.controller;

import com.example.lcmsapp.dto.GroupDTO;
import com.example.lcmsapp.entity.Group;
import com.example.lcmsapp.repository.GroupRepository;
import com.example.lcmsapp.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    //save
    public ResponseEntity<?> save(@RequestBody GroupDTO groupDTO) {

        return null;
    }

    //getOne


    //getAll va pagination va search va filtr byCourse filterbyFilial


    //update

    //delete


}
