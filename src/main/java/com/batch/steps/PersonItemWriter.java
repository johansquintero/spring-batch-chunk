package com.batch.steps;

import com.batch.domain.dto.PersonDto;
import com.batch.domain.services.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class PersonItemWriter implements ItemWriter<PersonDto> {

    @Autowired
    private IPersonService personService;

    @Override
    public void write(List<? extends PersonDto> list) throws Exception {
        list.forEach(
                personDto -> log.info(personDto.toString())
        );
        this.personService.saveAll((List<PersonDto>) list);
    }
}
