package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.dto.PaymentDto;
import com.example.lcmsapp.entity.Filial;
import com.example.lcmsapp.entity.Group;
import com.example.lcmsapp.entity.Payment;
import com.example.lcmsapp.entity.Student;
import com.example.lcmsapp.entity.enums.PayType;
import com.example.lcmsapp.exception.ResourceNotFoundException;
import com.example.lcmsapp.repository.FilialRepository;
import com.example.lcmsapp.repository.GroupRepository;
import com.example.lcmsapp.repository.PaymentRepository;
import com.example.lcmsapp.repository.StudentRepository;
import com.example.lcmsapp.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:57 PM on 6/26/2022
 * @project Edu-Center
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final PaymentRepository paymentRepository;

    private final FilialRepository filialRepository;
    private final DateFormatUtil dateFormat;

    public ApiResponse<Payment> save(PaymentDto paymentDto) {

        Student student = studentRepository.findById(UUID.fromString(paymentDto.getStudentId())).orElseThrow(() -> new ResourceNotFoundException("student", "id", paymentDto.getStudentId()));

        Filial filial = filialRepository.findById(paymentDto.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "id", paymentDto.getFilialId()));
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setFilial(filial);
        payment.setAmount(paymentDto.getAmount());

        for (PayType value : PayType.values()) {
            if (value.toString().equals(paymentDto.getPayType())) {
                payment.setPayType(value);
            }
        }
        return new ApiResponse<>("saved", paymentRepository.save(payment), true);
    }

    public ApiResponse<Payment> update(String id, PaymentDto paymentDto) {

        Payment payment = paymentRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException("payment", "id", id));

        Student student = studentRepository.findById(UUID.fromString(paymentDto.getStudentId())).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));

        Filial filial = filialRepository.findById(paymentDto.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "id", id));

        payment.setId(UUID.fromString(id));
        payment.setFilial(filial);
        payment.setStudent(student);
        payment.setAmount(paymentDto.getAmount());
        payment.setPayType(PayType.valueOf(paymentDto.getPayType()));
        Payment save = paymentRepository.save(payment);

        return new ApiResponse<>("updated", save, true);
    }


    public ApiResponse<Payment> getOne(String id) {
        Payment payment = paymentRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException("payment", "id", id));
        return new ApiResponse<>("GetOne", payment, true);
    }

    public ApiResponse<String> delete(String id) {
        paymentRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException("payment", "id", id));
        paymentRepository.deleteById(UUID.fromString(id));
        return new ApiResponse<>("Deleted by id", id, true);
    }


    public ApiResponse<?> getAll(int page, int size, String filial, String student, String group, String startDate, String endDate) {
        Date stDate = dateFormat.stringtoDate(startDate);
        Date enDate = dateFormat.stringtoDate(endDate);
        Page<Payment> payments = null;
//        Pageable pageable = PageRequest.of(page, size);

        List<Group> groupList = groupRepository.findAllByNameContainingIgnoreCase(group);


        if (filial.equals("") && student.equals("") && group.equals("")) {
            payments = paymentRepository.findAllByCreatedAtBetween(stDate, enDate);
        }
        // filial yoki student
//        else if (group.equals("")) {
//           payments =  paymentRepository.findAllFilial_NameContainingIgnoreCaseOrStudent_FullNameContainingIgnoreCase(filial, student);
//
//        }
        // filial yoki guruh
        else if (student.equals("")) {
            payments = paymentRepository.findAllByFilial_NameOrFilial_Groups(filial, groupList);
        }
        return ApiResponse.builder().data(payments).success(true).message("ok").build();
    }
}
