package com.ho.demo;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Implementation of the CSV parser that will return a list of {@link} VehicleInfo
 * We use the Apache CSV parser to do the job
 * 
 * @author mouli
 *
 */
public class CsvFileParser implements FileParser {
	
	private static final Logger LOGGER = Logger.getLogger(CsvFileParser.class.getName());
	private String[] HEADERS = { "registration", "make", "colour"};

	@Override
	public List<VehicleInfo> getVehicles(FileInfo fileInfo) {
		Iterable<CSVRecord> records = null;
		List<VehicleInfo> vehiclesInfo = new ArrayList<VehicleInfo>();
		try {
			final Reader in = new FileReader(fileInfo.getPath());
			final CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader(HEADERS));
			
			boolean valid = isFileValid(parser);
			
			if(valid) {
                records = parser.getRecords();
                for (CSVRecord record : records) {
                	long recordNumber = record.getRecordNumber();
                	//omit first vrecord as it is the header
                	if (recordNumber == 1) {
                		continue;
                	}
                	String registration = record.get("registration");
                	String make = record.get("make");
                	String colour = record.get("colour");
                	VehicleInfo vehicleInfo = new VehicleInfo(registration, colour, make);
                	vehiclesInfo.add(vehicleInfo);
                }
            }
            else {
                LOGGER.log(Level.INFO, "File {0} is not valid", fileInfo.getPath());
            }
			
		}//catch all exceptions here as the behaviour will be same for any exception 
		catch (Exception e) {
			LOGGER.log(Level.SEVERE,"Cannot parse file due to Exception", e);
		} 
		return vehiclesInfo;
	}

	/**
	 * Validate that the file contains the headers that we expect and nothing else
	 * 
	 * @param parser
	 * @return
	 */
	protected boolean isFileValid(CSVParser parser) {
		Map<String, Integer> headerMap = parser.getHeaderMap();
		
		if (headerMap.size() !=3 ) {
			return false;
		}
		
		return true;
	}

}
