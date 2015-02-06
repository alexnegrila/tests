package org.alexcoders.dataMigrator.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.alexcoders.dataMigrator.dtos.DataDTO;
import org.alexcoders.dataMigrator.models.Data;
import org.alexcoders.dataMigrator.repositories.DataRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	JobLauncher jobLauncher;

	@Resource
	Step step1;

	@Resource
	Job importDataJob;

	@Resource
	FlatFileItemReader<DataDTO> reader;

	@Autowired
	DataRepository dataRepository;

	@Override
	public List<Data> getAllData() {
		Iterable<Data> dataItems = dataRepository.findAll();
		if (dataItems instanceof List) {
			return (List<Data>) dataItems;
		}
		List<Data> newList = new ArrayList<Data>();
		Iterator<Data> iterator = dataItems.iterator();
		while (iterator.hasNext()) {
			newList.add(iterator.next());
		}
		return newList;
	}

	@Override
	public void importDataFrom(String pathname) throws FileNotFoundException {
		File file = new File(pathname);
		if (file.exists()) {
			reader.setResource(new FileSystemResource(file));
			try {
				jobLauncher.run(importDataJob, new JobParameters());
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();
			}
			// jobBuilderFactory.get("importDataFromJob")
			// .incrementer(new RunIdIncrementer())
			// .flow(step1)
			// .end()
			// .build();
		} else {
			throw new FileNotFoundException(pathname);
		}

	}

}
