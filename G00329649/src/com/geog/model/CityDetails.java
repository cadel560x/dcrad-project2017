package com.geog.model;

// @ManagedBean annotation is inherited
public class CityDetails extends City {
//	Instance variables
	private String countryName;
	private String regionName;

	
	
	
//	Constructors
	public CityDetails() {
	}

	public CityDetails(String code, String name, int population, boolean coastal, double area, String co_code,
			String reg_code, String countryName, String regionName) {
		super(code, name, population, coastal, area, co_code, reg_code);
		this.countryName = countryName;
		this.regionName = regionName;
	}

	
	
	
//	Accessors and mutators	
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
} // class CityDetails
