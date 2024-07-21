package com.batch.domain.services;

import com.batch.domain.dto.PersonDto;

import java.util.List;

public interface IPersonService {
    List<PersonDto> saveAll(List<PersonDto> personDtoList);
    List<PersonDto> getAll();
}
