package org.alexcoders.dataMigrator;

import java.io.FileNotFoundException;
import java.util.List;

import org.alexcoders.dataMigrator.models.Data;
import org.alexcoders.dataMigrator.services.DataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public class DataMigratorIntegrationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataService dataService;

	@Test
	public void test() throws FileNotFoundException {
		
		List<Data> allData = dataService.getAllData();
		
		for (int i = 0; i < allData.size(); i++) {
			Data data = allData.get(i);
			System.out.println("Found data #" + i + " in the database.(" + data.toString() + ")");
		}

		Assert.assertTrue("bad", allData.size() == 70);

		// Old way
		// StringBuilder query = new StringBuilder();
		// query.append("SELECT ");
		// query.append(Data.ID).append(", ").append(Data.ARTICLEID).append(", ").append(Data.ATTRIBUTE);
		// query.append(", ").append(Data.VALUE).append(", ").append(Data.LANGUAGE).append(", ").append(Data.TYPE);
		// query.append(" FROM ").append(Data.TABLE_NAME);
		// List<Data> results = jdbcTemplate.query(query.toString(), new
		// RowMapper<Data>() {
		//
		// @Override
		// public Data mapRow(ResultSet rs, int row) throws SQLException {
		// Data data = new Data();
		// data.setId(rs.getInt(Data.ID));
		// data.setValue(rs.getString(Data.VALUE));
		// return data;
		// }
		// });
	}

}