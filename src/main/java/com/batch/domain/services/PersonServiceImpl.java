package com.batch.domain.services;

import com.batch.domain.dto.PersonDto;
import com.batch.persistence.entities.repositories.IPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements IPersonService{
    private final IPersonRepository personRepository;

    @Override
    public List<PersonDto> saveAll(List<PersonDto> personDtoList) {
        return this.personRepository.saveAll(personDtoList);
    }

    @Override
    public List<PersonDto> getAll() {
        return this.personRepository.getAll();
    }
}
