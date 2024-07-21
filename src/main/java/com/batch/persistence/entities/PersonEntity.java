package com.batch.persistence.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "person")
@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;
    private int age;

    @Column(name = "created_at")
    private String createdAt;
}
