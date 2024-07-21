package com.batch.persistence.entities.repositories;

import com.batch.domain.dto.PersonDto;

import java.util.Iterator;
import java.util.List;

public interface IPersonRepository {
    List<PersonDto> saveAll(List<PersonDto> personDtoList);
    List<PersonDto> getAll();
}
