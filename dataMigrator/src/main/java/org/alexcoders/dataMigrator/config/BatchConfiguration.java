package org.alexcoders.dataMigrator.config;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.alexcoders.dataMigrator.dtos.DataDTO;
import org.alexcoders.dataMigrator.models.Data;
import org.alexcoders.dataMigrator.processors.DataItemProcessor;
import org.alexcoders.dataMigrator.repositories.DataRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final char QUOTE_CHAR = '"';
    
    @Autowired
    DataRepository dataRepository;
    
    @Autowired
    private Validator validator;
    
    @Bean
    public Validator getValidator() {
		return new LocalValidatorFactoryBean();
	}
    
	// tag::readerwriterprocessor[]
    @Bean
    public ItemReader<DataDTO> reader() {
        FlatFileItemReader<DataDTO> reader = new FlatFileItemReader<DataDTO>();
        reader.setResource(new ClassPathResource("test.csv"));
        reader.setStrict(false);
        reader.setLinesToSkip(1);
        //deal with text separated by quotes
        reader.setRecordSeparatorPolicy(new RecordSeparatorPolicy() {
			
			@Override
			public String preProcess(String record) {
				return record;
			}
			
			@Override
			public String postProcess(String record) {
				return record;
			}
			
			@Override
			public boolean isEndOfRecord(String record) {
				int occurencesOfQuote = StringUtils.countOccurrencesOf(record, String.valueOf(QUOTE_CHAR));
				return occurencesOfQuote%2==0;
			}
		});;
        reader.setLineMapper(new DefaultLineMapper<DataDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { Data.ID, Data.ARTICLEID, Data.ATTRIBUTE, Data.VALUE, Data.LANGUAGE, Data.TYPE });
                //use unified quote character
                setQuoteCharacter(QUOTE_CHAR);
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<DataDTO>() {
			{
                setTargetType(DataDTO.class);
                
                Map<Class<Short>, PropertyEditorSupport> customEditors = new HashMap<Class<Short>, PropertyEditorSupport>();
                //deal with "empty" value in LANGUAGE field
                customEditors.put(Short.class, new CustomNumberEditor(Short.class, true){
                	public void setAsText(String text) throws IllegalArgumentException {
                		if (text.equals("empty")) {
                			text = null;
						}
                		super.setAsText(text);
                	};
                });
				setCustomEditors(customEditors);
            }
            @Override
            protected DataBinder createBinder(Object target) {
            	DataBinder binder = super.createBinder(target);
            	//injected validator into binder
            	binder.setValidator(BatchConfiguration.this.validator);
				return binder;
            	
            };
            });
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<DataDTO, Data> processor() {
        return new DataItemProcessor();
    }

    @Bean
    public ItemWriter<Data> writer(EntityManagerFactory entityManagerFactory) {
    	JpaItemWriter<Data> jpaItemWriter= new JpaItemWriter<Data>();
    	jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
 
        return jpaItemWriter;
       	//Old way
//      JdbcBatchItemWriter<Data> writer = new JdbcBatchItemWriter<Data>();
//      writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Data>());
//      StringBuilder query = new StringBuilder();
//      query.append("INSERT INTO ").append(Data.TABLE_NAME);
//      query.append(" (").append(Data.ID).append(", ").append(Data.ARTICLEID).append(", ").append(Data.ATTRIBUTE);
//      query.append(", ").append(Data.VALUE).append(", ").append(Data.LANGUAGE).append(", ").append(Data.TYPE).append(") ");
//      query.append("VALUES (:").append(Data.ID).append(", :").append(Data.ARTICLEID).append(", :").append(Data.ATTRIBUTE);
//      query.append(", :").append(Data.VALUE).append(", :").append(Data.LANGUAGE).append(", :").append(Data.TYPE).append(") ");
//      writer.setSql(query.toString());
//      writer.setDataSource(dataSource);
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importDataJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importDataJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<DataDTO> reader,
            ItemWriter<Data> writer, ItemProcessor<DataDTO, Data> processor) {
        return stepBuilderFactory.get("step1")
                .<DataDTO, Data> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    // end::jobstep[]

    //inject bean using annotation
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}