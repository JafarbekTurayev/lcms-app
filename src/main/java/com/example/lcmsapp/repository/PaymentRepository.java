package com.example.lcmsapp.repository;


import com.example.lcmsapp.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
      // hammasi paga va size
      // filial buyicha
      // student buyicha
      // data buyicha 1 - 10 gacha
      // (vaqt va page buyicha)
      Page<Payment> findAllByCreatedAtBetween(Date start, Date end, Pageable pageable);

      Page<Payment> findAllByCreatedAtBetweenOrStudent_FullName(Date start,Date end,String student,Pageable pageable);
      Page<Payment> findAllByCreatedAtBetweenOrFilial_Name(Date start,Date end,String filial,Pageable pageable);

      Page<Payment> findAllByCreatedAtBetweenOrStudent_FullNameAndFilial_Name(Date start,Date end,String full_name,String name, Pageable pageable);



}
