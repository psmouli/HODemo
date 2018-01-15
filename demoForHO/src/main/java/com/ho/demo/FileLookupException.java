package com.ho.demo;

/**
 * Exception that indicates an error was encountered during file lookup
 * @author mouli
 *
 */
public class FileLookupException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FileLookupException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileLookupException(String message) {
		super(message);
	}
}
