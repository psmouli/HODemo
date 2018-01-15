package com.ho.demo;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class FilesExtractorTest {
	
	@Spy
	private FilesExtractor fileExtractor;
	
	@Mock
	private File directoryMock;
	
	private static final String DIRECTORY_PATH = "/Users/mouli/Documents/workspace/demoForHO/src/test/resources";
	
	@Before
	public void setup() throws Exception{
		fileExtractor = new FilesExtractor();
		initMocks(this);
		
		doReturn(new File(DIRECTORY_PATH)).when(fileExtractor).getSourceDirectory();
		
	}
	
	@Test
	public void testScanDirectoryForAllFiles() throws Exception{
		List<File> validFilesInDirectory = fileExtractor.scanDirectory();
		
		assertEquals(4, validFilesInDirectory.size());
	}

}
