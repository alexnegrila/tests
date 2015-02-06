package org.alexcoders.dataMigrator.services;

import java.io.FileNotFoundException;
import java.util.List;

import org.alexcoders.dataMigrator.models.Data;

public interface DataService {

	List<Data> getAllData();

	void importDataFrom(String pathname) throws FileNotFoundException;
}
