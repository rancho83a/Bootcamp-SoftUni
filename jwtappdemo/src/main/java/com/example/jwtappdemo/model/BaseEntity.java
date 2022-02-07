package com.example.jwtappdemo.model;

import com.example.jwtappdemo.model.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;

    @Enumerated(EnumType.STRING)
    private Status status;
}

