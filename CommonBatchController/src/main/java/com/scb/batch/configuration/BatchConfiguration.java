package com.scb.batch.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.scb.batch.item.CountryItemProcessor;
import com.scb.batch.model.Country;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Bean
	public ItemReader<Country> reader() {
		FlatFileItemReader<Country> reader = new FlatFileItemReader<Country>();
		reader.setResource(new ClassPathResource("sample-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<Country>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "countryCode", "description" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Country>() {
					{
						setTargetType(Country.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemProcessor<Country, Country> processor() {
		return new CountryItemProcessor();
	}

	@Bean
	public ItemWriter<Country> writer(DataSource dataSource) {
		JdbcBatchItemWriter<Country> writer = new JdbcBatchItemWriter<Country>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Country>());
		writer.setSql("INSERT INTO country (country_code, description) VALUES (:countryCode, :description)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importCountryCode").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}

	@Bean
	public Step processCountryCode(StepBuilderFactory stepBuilderFactory, ItemReader<Country> reader,
			ItemWriter<Country> writer, ItemProcessor<Country, Country> processor) {
		return stepBuilderFactory.get("processCountryCode").<Country, Country> chunk(10).reader(reader)
				.processor(processor).writer(writer).build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}


}
