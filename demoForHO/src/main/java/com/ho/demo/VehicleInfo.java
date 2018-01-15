package com.ho.demo;

/**
 * A POJO containing the basic info about the car
 * 
 * @author mouli
 *
 */
public class VehicleInfo {
	
	private String registrationNumber;
	private String colour;
	private String make;
	
	public VehicleInfo(String registrationNumber, String colour, String make) {
		setRegistrationNumber(registrationNumber);
		setMake(make);
		setColour(colour);
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}

}
