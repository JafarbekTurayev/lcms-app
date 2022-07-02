package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.TeacherDTO;
import com.example.lcmsapp.entity.Course;
import com.example.lcmsapp.entity.Filial;
import com.example.lcmsapp.entity.Teacher;
import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.exception.ResourceNotFoundException;
import com.example.lcmsapp.repository.CourseRepository;
import com.example.lcmsapp.repository.FilialRepository;
import com.example.lcmsapp.repository.TeacherRepository;
import com.example.lcmsapp.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final DateFormatUtil dateFormatUtil;

    public ApiResponse add(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        if (teacherDTO.getCourseId() != null) {
            Course course = courseRepository.findById(teacherDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("course", "id", teacherDTO.getCourseId()));
            teacher.setCourse(course);
        }
        teacher.setAddress(teacherDTO.getAddress());
        teacher.setActive(teacherDTO.isActive());
        teacher.setBalance(teacherDTO.getBalance());
//        if (teacherDTO.getMale().toString().length()>1)
        teacher.setMale(teacherDTO.getMale());
        teacher.setDateOfBirth(dateFormatUtil.strToLocalDate(teacherDTO.getDateOfBirth()));
        teacher.setFullName(teacherDTO.getFullName());
        teacher.setPassportDateOfIssue(dateFormatUtil.strToLocalDate(teacherDTO.getPassportDateOfIssue()));
        teacher.setPassportGivenBy(teacherDTO.getPassportGivenBy());
        teacher.setPassportNo(teacherDTO.getPassportNo());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setPositionType(teacherDTO.getPositionType());


        Teacher save = teacherRepository.save(teacher);
        //maptoDTO
        return ApiResponse.builder().data(save).message("Saved").success(true).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Teacher teacher = teacherRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Bunday UUID-li teacher topilmadi"));
        return ApiResponse.builder().message("Mana").success(true).data(teacher).build();

    }

    public ApiResponse getAll() {
        return ApiResponse.builder().data(teacherRepository.findAll()).success(true).message("Mana").build();
    }

    public ApiResponse update(UUID uuid, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Bunaqa Teacher topilmadi"));
        teacher.setAddress(teacherDTO.getAddress());
        teacher.setActive(teacherDTO.isActive());
        teacher.setBalance(teacherDTO.getBalance());
        teacher.setMale(teacherDTO.getMale());
        teacher.setDateOfBirth(dateFormatUtil.strToLocalDate(teacherDTO.getDateOfBirth()));
        teacher.setFullName(teacherDTO.getFullName());
        teacher.setPassportDateOfIssue(dateFormatUtil.strToLocalDate(teacherDTO.getPassportDateOfIssue()));
        teacher.setPassportGivenBy(teacherDTO.getPassportGivenBy());
        teacher.setPassportNo(teacherDTO.getPassportNo());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setPositionType(teacherDTO.getPositionType());

        if (teacherDTO.getCourseId() != null) {
            Course course = courseRepository.findById(teacherDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("course", "id", teacherDTO.getCourseId()));
            teacher.setCourse(course);
        }
        teacherRepository.save(teacher);
        return ApiResponse.builder().message("Updated!!!").success(true).data(teacher).build();
    }

    public ApiResponse delete(UUID uuid){
        Teacher teacher = teacherRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Not Found!!!"));
        teacherRepository.deleteById(uuid);
        return ApiResponse.builder().data(teacher).success(true).message("Deleted!!!").build();
    }
}
