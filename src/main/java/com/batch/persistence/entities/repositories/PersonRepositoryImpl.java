package com.batch.persistence.entities.repositories;

import com.batch.domain.dto.PersonDto;
import com.batch.persistence.entities.PersonEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class PersonRepositoryImpl implements IPersonRepository{
    private final IpersonCrudRepository crudRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PersonDto> saveAll(List<PersonDto> personDtoList) {
        List<PersonEntity> personEntityList = personDtoList
                .stream()
                .map(personDto -> this.modelMapper.map(personDto,PersonEntity.class))
                .collect(Collectors.toList());
        return this.crudRepository.saveAll(personEntityList).stream().map(
                personEntity -> this.modelMapper.map(personEntity,PersonDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> getAll() {
        return this.crudRepository.findAll()
                .stream()
                .map(personEntity -> this.modelMapper.map(personEntity,PersonDto.class))
                .collect(Collectors.toList());
    }
}
