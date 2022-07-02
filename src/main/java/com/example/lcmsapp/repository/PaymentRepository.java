package com.example.lcmsapp.repository;

import com.example.lcmsapp.entity.Group;
import com.example.lcmsapp.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
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
    //  guruh buyicha
    //  data buyicha 1 - 10 gacha

    //            (vaqt va page buyicha)
    Page<Payment> findAllByCreatedAtBetween(Date start, Date end);


    //filial name va grouplist  (vaqt va page buyicha)
//      Page<Payment> findAllByFilial_NameOrFilial_Groups ( String filial, List<Group> groupList);
//TODO native yozib ko'rish kerak

    // filial name va student name (vaqt va page buyicha)
//       Page<Payment> findAllFilial_NameContainingIgnoreCaseOrStudent_FullNameContainingIgnoreCase( String filial, String student);


}
