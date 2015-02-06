package org.alexcoders.dataMigrator;

import java.io.FileNotFoundException;
import java.util.List;

import org.alexcoders.dataMigrator.models.Data;
import org.alexcoders.dataMigrator.services.DataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) throws FileNotFoundException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		DataService dataService = ctx.getBean(DataService.class);
		//list all data imported from the resources/test.csv file
		listAllData(dataService);
		
		if (args.length > 0) {
			//list all data imported from the user provided file
			String pathname = args[0];
			dataService.importDataFrom(pathname);

			listAllData(dataService);
		}
	}

	private static void listAllData(DataService dataService) {
		List<Data> allData = dataService.getAllData();
		for (int i = 0; i < allData.size(); i++) {
			Data data = allData.get(i);
			System.out.println("Found data #" + i + " in the database.(" + data.toString() + ")");
		}
	}

}