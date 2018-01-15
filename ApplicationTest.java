package com.ho.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class 
 * @author mouli
 *
 */
public class ApplicationTest {
	
	Application testClass = new Application();
	
	FileInfo mockCsvFileInfo;
	FileInfo mockXlsFileInfo;
	

	@Before
	public void setup() {
		mockCsvFileInfo = new FileInfo("testFile.csv", "txt/csv", "csv", 200, "/Users/somePath/testFile.csv");
		mockXlsFileInfo = new FileInfo("testFile.csv", "txt/xsl", "xsl", 200, "/Users/somePath/testFile.csv");
		
	}
	
	@Test
	public void testParserForCsvFileExtension() throws Exception {
		FileParser  csvFileParser = testClass.getFileParser(mockCsvFileInfo);
		assertEquals(csvFileParser.getClass().getName(), "com.ho.demo.CsvFileParser");
	}
	
	
	@Test
    public void testParserForXslFileExtension() throws Exception {
		FileParser  xlsFileParser = testClass.getFileParser(mockXlsFileInfo);
		assertEquals(xlsFileParser.getClass().getName(), "com.ho.demo.ExcelFileParser");
	}
	
	
}
