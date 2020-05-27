package com.coveo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coveo.dto.Cities;

@Component
public class ReadTSV {
	
	List<String> cityName = new ArrayList<String>();
	HashMap<String, Cities> map = new HashMap<String, Cities>();
	AutoComplete autocomplete;
	
	void readFile() {
		BufferedReader TSVFile;
		int count = 0;
		try {
			TSVFile = new BufferedReader(
					new FileReader("C:\\Users\\cheta\\OneDrive\\Desktop\\cities_canada-usa.tsv.txt"));

			String dataRow = TSVFile.readLine(); // Read first line.

			while (dataRow != null) {
				if(count>=1) {

					String fields[] = dataRow.split("\t");
					Cities city = new Cities();
					
					
					cityName.add(fields[1]);
					city.setName(fields[1]);
					city.setLatitude(Float.parseFloat(fields[4]));
					city.setLongitude(Float.parseFloat(fields[5]));
					map.put(fields[1], city);
				}
				count++;
				dataRow = TSVFile.readLine(); // Read next line of data.
			}
			// Close the file once all data has been read.
			TSVFile.close();
			autocomplete = new AutoComplete(cityName, this);
		} catch (FileNotFoundException e) {
			System.out.println("file not exception " + e);
		} catch (IOException e) {
			System.out.println("io exception " + e);
		}
	}

}
