package com.batch.config;

import com.batch.domain.dto.PersonDto;
import com.batch.steps.PersonItemProcessor;
import com.batch.steps.PersonItemReader;
import com.batch.steps.PersonItemWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Bean
    public PersonItemReader personItemReader(){
        return new PersonItemReader();
    }

    @Bean
    public PersonItemWriter personItemWriter(){
        return new PersonItemWriter();
    }

    @Bean
    public PersonItemProcessor personItemProcessor(){return new PersonItemProcessor();}
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //cantidad de hilos al iniciar
        taskExecutor.setCorePoolSize(1);
        //desplegar cantidad maxima de hilos
        taskExecutor.setMaxPoolSize(2);
        //cantidad de tareas en cola
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

    @Bean
    public Step readFile(){
        return this.stepBuilderFactory.get("readFile")
                .<PersonDto,PersonDto>chunk(10)
                .reader(this.personItemReader())
                .processor(this.personItemProcessor())
                .writer(this.personItemWriter())
                .taskExecutor(this.taskExecutor())
                .build();
    }



    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("readFileChunk")
                .start(readFile()).build();
    }
}
