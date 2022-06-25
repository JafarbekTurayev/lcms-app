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
import org.springframework.stereotype.Service;

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


}
