package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.GroupDTO;
import com.example.lcmsapp.entity.Course;
import com.example.lcmsapp.entity.Filial;
import com.example.lcmsapp.entity.Group;
import com.example.lcmsapp.exception.ResourceNotFoundException;
import com.example.lcmsapp.repository.CourseRepository;
import com.example.lcmsapp.repository.FilialRepository;
import com.example.lcmsapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final FilialRepository filialRepository;
    private final CourseRepository courseRepository;

    public ApiResponse add(GroupDTO groupDTO) {
        Filial filial = filialRepository.findById(groupDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "id", groupDTO.getFilialId()));
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("course", "id", groupDTO.getCourseId()));
        Group group = new Group();
        group.setCourse(course);
        group.setFilial(filial);
        //nomini saqlashdan oldin tekshirish shu filialda shunaqa oldin guruh ochilmaganmi?
        if (groupRepository.existsByNameAndFilial_IdAndCourse_Id(groupDTO.getName(), groupDTO.getFilialId(), groupDTO.getCourseId()))
            throw new RuntimeException("Bunday nomli guruh mavjud!!!");
        group.setName(groupDTO.getName());
        Group save = groupRepository.save(group);
        //maptoDTO
        return ApiResponse.builder().data(save).message("Saved").success(true).build();
    }

    public ApiResponse<Group> getOne(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group", "id", id));
        return new ApiResponse("Mana", group, true);
    }


    public ApiResponse remove(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group", "id", id));
        groupRepository.delete(group);


        if (!groupRepository.existsById(id)) return new ApiResponse("Bunday idli guruh yo'q", false);
        groupRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }

    public ApiResponse getAll(int page, int size, String search, String filialName, String courseName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Group> data = null;

        //findAll 1-variant
        if (search.equals("") && filialName.equals("") && courseName.equals("")) {
            data = groupRepository.findAll(pageable);
        }
//        //page va size 2-pageable
//        else if (search.equals("") && filialName.equals("") && courseName.equals("")) {
//            //Pageable
//            data = groupRepository.findAll(pageable);
//        }
        //search 3-variant
//        else if (filialName.equals("") && courseName.equals("")) {
//            data = groupRepository.findAllByNameContainingIgnoreCase(search, pageable);
//        }
//        //filialName 4-variant
//        else if (search.equals("") && courseName.equals("")) {
//            data = groupRepository.findAllByFilial_NameContainingIgnoreCase(filialName, pageable);
//        }
//        //coruseName 5-variant
//        else if (search.equals("") && filialName.equals("")) {
//            data = groupRepository.findAllByCourse_NameContainingIgnoreCase(courseName, pageable);
//        }
        else {
            data = groupRepository.findAllByFilial_NameContainingIgnoreCaseAndCourse_NameContainingIgnoreCaseAndNameContainingIgnoreCase(filialName, courseName, search, pageable);
        }
        return ApiResponse.builder().data(data).message("OK").success(true).build();
    }

    public ApiResponse edit(Long id, GroupDTO groupDTO) {
        Group group = groupRepository.findById(id).orElseThrow();
        Filial filial = filialRepository.findById(groupDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "id", groupDTO.getFilialId()));
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("course", "id", groupDTO.getCourseId()));

        group.setName(groupDTO.getName());
        group.setCourse(course);
        group.setFilial(filial);
        Group save = groupRepository.save(group);
        return ApiResponse.builder().success(true).message("Edited!").data(save).build();
    }
}

