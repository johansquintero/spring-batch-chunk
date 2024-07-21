package com.batch.steps;

import com.batch.domain.dto.PersonDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public class PersonItemReader extends FlatFileItemReader<PersonDto> {
    public PersonItemReader(){
        setName("readPersons");
        setResource(new ClassPathResource("persons.csv"));
        setLinesToSkip(1);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(this.lineMapper());
    }

    public LineMapper<PersonDto> lineMapper(){
        //con estas dos instancias el archivo va a poder ser leido por el flatfileitemreader
        DefaultLineMapper<PersonDto> lineMapper = new DefaultLineMapper<>();
        //en especicifo esta instancia ayuda ayuda a realizar la separacion de atributos de cada linea
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        String[] columns = new String[]{"name","lastName","age"};
        int[] indexFields = new int[]{0,1,2};

        lineTokenizer.setNames(columns);
        lineTokenizer.setIncludedFields(indexFields);
        lineTokenizer.setDelimiter(",");

        //realiza el mapeo de los registros con el objeto de tipo personaDto
        BeanWrapperFieldSetMapper<PersonDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PersonDto.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
