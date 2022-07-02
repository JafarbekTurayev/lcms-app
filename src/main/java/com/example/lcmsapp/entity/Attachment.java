package com.example.lcmsapp.entity;


import com.example.lcmsapp.entity.template.AbsEntity;
import com.example.lcmsapp.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attachment extends AbsEntity {
    private String fileName;
    private String contentType; //.xls .doc
    private long size;
    private String url; //url
}
