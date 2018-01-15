package com.ho.demo;

import java.util.List;

/**
 * An interface that will be implemented by the implementing parsers
 * The requirement is to parse csv and excel files
 * 
 * @author mouli
 *
 */
public interface FileParser {
	
	public List<VehicleInfo> getVehicles(FileInfo fileInfo); 

}
