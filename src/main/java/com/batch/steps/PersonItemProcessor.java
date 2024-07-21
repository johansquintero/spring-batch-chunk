package com.batch.steps;

import com.batch.domain.dto.PersonDto;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonItemProcessor implements ItemProcessor<PersonDto,PersonDto> {

    @Override
    public PersonDto process(PersonDto personDto) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        personDto.setCreatedAt(formatter.format(LocalDateTime.now()));
        return personDto;
    }
}
