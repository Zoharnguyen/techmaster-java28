package com.techmaster.springbatch.config;

import com.techmaster.springbatch.model.Order;
import com.techmaster.springbatch.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.StringUtils;

@Configuration
@Slf4j
public class OrderBatchConfig {

    @Autowired
    private OrderRepository OrderRepository;

    @Bean
    public Job importOrderJob(JobRepository jobRepository, Step importOrderStep) {
        return new JobBuilder("importOrderJob", jobRepository)
                .start(importOrderStep)
                .build();
    }

    @Bean
    public Step importOrderStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                             ItemReader<Order> reader, ItemProcessor<Order, Order> processor,
                             ItemWriter<Order> writer) {
        return new StepBuilder("importOrderStep", jobRepository)
                .<Order, Order>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Order> reader() {
        FlatFileItemReader<Order> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("Orders.csv"));
        reader.setLinesToSkip(1);

        DefaultLineMapper<Order> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "quantity", "price");

        BeanWrapperFieldSetMapper<Order> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Order.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemProcessor<Order, Order> processor() {
        return order -> {
            try {

                if (!StringUtils.isEmpty(order.getQuantity()) && Integer.valueOf(order.getQuantity()) > 10) {
                    log.info("Skip order information of info level {}", order);
                    return null;
                }

                if (!StringUtils.isEmpty(order.getPrice()) && Integer.valueOf(order.getPrice()) < 100) {
                    log.warn("Skip order information warn level {}", order);
                    return null;
                }

            } catch (NumberFormatException exp) {
                log.error("Error {}", exp.getCause());
            }


            return order;
        };
    }

    @Bean
    public ItemWriter<Order> writer() {
        return items -> {
            for (Order order : items) {
                if (order != null) {
                    OrderRepository.save(order);
                }
            }
        };
    }
} 