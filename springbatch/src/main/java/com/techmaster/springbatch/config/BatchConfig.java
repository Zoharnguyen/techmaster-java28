package com.techmaster.springbatch.config;

import com.techmaster.springbatch.model.User;
import com.techmaster.springbatch.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step importUserStep) {
        return new JobBuilder("importUserJob", jobRepository)
                .start(importUserStep)
                .build();
    }

    @Bean
    public Step importUserStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                             ItemReader<User> reader, ItemProcessor<User, User> processor,
                             ItemWriter<User> writer) {
        return new StepBuilder("importUserStep", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("users.csv"));
        reader.setLinesToSkip(1);

        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "email");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemProcessor<User, User> processor() {
        return user -> {
            if (user.getEmail() != null && user.getEmail().contains("@")) {
                return user;
            }
            return null;
        };
    }

    @Bean
    public ItemWriter<User> writer() {
        return items -> {
            for (User user : items) {
                if (user != null) {
                    userRepository.save(user);
                }
            }
        };
    }
} 