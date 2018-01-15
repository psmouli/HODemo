package com.ho.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The class that will scan the configured directories for files that need to be parsed
 * 
 * @author mouli
 *
 */
@Component
public class FilesExtractor {
	private static final Logger LOGGER = Logger.getLogger(FilesExtractor.class.getName());
	
	@Value("${directory}")
	private String sourceDirectory;
	
	@Value("${fileNamePattern}")
	private String pattern;


	/**
	 * 
	 * @return the list of files that need to be parsed
	 */
	public List<File> scanDirectory() throws FileLookupException{
		LOGGER.log(Level.FINE ,"Reading properties from {0}",sourceDirectory);
		File directory = new File(sourceDirectory);
		if (!directory.exists() || !directory.isDirectory()) {
			throw new FileLookupException("Unable to scan for new files as the configured 'directory' is invalid: " + sourceDirectory);
		}
		
		List<File> allFiles = Arrays.asList(directory.listFiles());
		if (allFiles == null || allFiles.isEmpty()) {
			LOGGER.log(Level.INFO ,"No files to process. Please check if the directory {0} where the files go is right", directory);
			return null;
		}
		
		List<File> validFiles = new ArrayList<File>();
		for (File file : allFiles) {
			if (! filterFile(file)) {
				LOGGER.log(Level.INFO, "The file {0} does not seem to be the right type", file.getName());
				
			} else {
				LOGGER.log(Level.INFO, "The file {0} seems to be the right type", file.getName());
				validFiles.add(file);
			}
		}
		
		return validFiles;
	}
	
	/**
	 * Given a list of files return the information required of the files
	 * 
	 * @param filesInDirectory
	 * @return
	 * @throws IOException
	 */
	public List<FileInfo> getFileInformation(List<File> filesInDirectory) throws IOException {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		for (File file : filesInDirectory) {
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			String type = mimeTypesMap.getContentType(file);
			String name = file.getName();
			String extension = FilenameUtils.getExtension(name);
			Long sizeInBytes = file.length();
			String path = file.getAbsolutePath();
			
			FileInfo fileInfo = new FileInfo(name, type, extension, sizeInBytes, path);
			fileInfos.add(fileInfo);
		}
		
		return fileInfos;
	}
	
	 /**
	   * Checks if the file should be imported or not. This implementation returns true if the file is not a hidden file.
	   * 
	   * @param file The file to check.
	   * @return true if the File is a valid file to import, false if not.
	   */
	  protected boolean filterFile(File file) {
	    return (!file.isHidden() && fileNameMatches(file));
	  }
	  
	  /**
	   * Checks whether a file name pattern is provided and if pattern exists
	   * match the file name with the pattern. If a file name pattern is not provided return true
	   * 
	   * @param file
	   * @return true if file name matches
	   */
	  protected boolean fileNameMatches(File file) {
	    if (pattern == null){
	      return true;
	    }
	    Pattern fileNamePattern = Pattern.compile(pattern);
	    return fileNamePattern.matcher(file.getName()).find();
	  }
	
}
