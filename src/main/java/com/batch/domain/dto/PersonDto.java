package com.batch.domain.dto;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private String LastName;
    private int age;
    private String createdAt;
}
