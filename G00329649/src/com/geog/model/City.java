package com.geog.model;

public class City {
//	Instance variables
	private String code;
	private String name;
	private int population;
	private float areaKM;
	
	
	
	
//	Constructor
	public City() {
	}




//	Accessors and mutators	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public float getAreaKM() {
		return areaKM;
	}

	public void setAreaKM(float areaKM) {
		this.areaKM = areaKM;
	}
		
} // class City
