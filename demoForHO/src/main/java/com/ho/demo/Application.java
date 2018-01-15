package com.ho.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Entry point to the main classes that will do the work
 * 
 * @author mouli
 *
 */
@Component
public class Application {
	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	/**
	 * This will invoke the process to scan the directory
	 */
	public List<VehicleInfo> startProcess()  {
		
		// read spring config file
		ClassPathXmlApplicationContext context = 
						new ClassPathXmlApplicationContext("applicationContext.xml");
		
		FilesExtractor filesExtractor = context.getBean("filesExtractor", FilesExtractor.class);
		List<VehicleInfo> allVehicleInfos = new ArrayList<VehicleInfo>();
		try {
			List<File> validFiles = filesExtractor.scanDirectory();
			LOGGER.log(Level.INFO, "Number of valid files = " + validFiles.size());
			System.out.println("Number of files = " + validFiles.size());
			List<FileInfo> fileInfos = filesExtractor.getFileInformation(validFiles);
			for (FileInfo fileInfo : fileInfos) {
				FileParser fileParser = getFileParser(fileInfo);
				List<VehicleInfo> vehicleInfos = fileParser.getVehicles(fileInfo);
				allVehicleInfos.addAll(vehicleInfos);
			}
			
			//catching all exceptions here. This means there is some serious issue
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"There is an issue with running the program Exception", e);
		} 
		finally {
			context.close();
		}
		
		return allVehicleInfos;
	}

	/**
	 * A basic utility method that returns the Parser based on the file extension.
	 * We have only 2 types of parsers to be returned either a CSV one or the Excel one
	 * 
	 * @param fileInfo
	 * @return
	 */
	protected static FileParser getFileParser(FileInfo fileInfo) {
		// we only support 2 types csv and excel parsers
		if ("csv".equals(fileInfo.getExtension())) {
			return new CsvFileParser();
		} else {
			return new ExcelFileParser();
		}
	}

}
